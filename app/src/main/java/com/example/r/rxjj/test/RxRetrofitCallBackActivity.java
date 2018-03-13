package com.example.r.rxjj.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.r.rxjj.R;
import com.example.r.rxjj.testEntity.Translation1;
import com.example.r.rxjj.testEntity.Translation2;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求嵌套回调
 */
public class RxRetrofitCallBackActivity extends AppCompatActivity {
    public static final String RR = "Rx嵌套回调";
    private Button mBt;
    private TextView mTv;

    Observable<Translation1> mObservable1;//注册
    Observable<Translation2> mObservable2;//登录
    private StringBuffer mBuffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit_call_back);
        mBt = (Button) findViewById(R.id.bt);
        mTv = (TextView) findViewById(R.id.tv);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toReqRx();
            }
        });
    }

    private void toReqRx() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RetrofitInter retrofitInter = retrofit.create(RetrofitInter.class);
        mObservable1 = retrofitInter.getCall_1();
        mObservable2 = retrofitInter.getCall_2();
        mObservable1.subscribeOn(Schedulers.io())//（初始被观察者）切换到IO线程进行网络请求1
                .observeOn(AndroidSchedulers.mainThread())//  （新观察者）切换到主线程 处理网络请求1的结果
                .doOnNext(new Consumer<Translation1>() {
                    @Override
                    public void accept(Translation1 translation1) throws Exception {
                        Log.e(RR, "第一次网络请求成功");
                        String ss = translation1.show();
                        printLog("第一次网络请求成功\n" + ss);
                    }
                }).observeOn(Schedulers.io())
                // （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求
                // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
                // 但对于初始观察者，它则是新的被观察者
                .flatMap(new Function<Translation1, ObservableSource<Translation2>>() {// 作变换，即作嵌套网络请求
                    @Override
                    public ObservableSource<Translation2> apply(Translation1 translation1) throws Exception {
                        // 将网络请求1转换成网络请求2，即发送网络请求2
                        return mObservable2;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Translation2>() {
                    @Override
                    public void accept(Translation2 o) throws Exception {
                        Log.e(RR, "第二次网络请求成功");
                        String sss = o.show();
                        printLog("第二次网络请求成功\n" + sss);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        printLog("登录失败" + throwable);
                    }
                });

    }

    private void printLog(String str) {
        mBuffer.append(str + "\n");
        mTv.setText(mBuffer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
