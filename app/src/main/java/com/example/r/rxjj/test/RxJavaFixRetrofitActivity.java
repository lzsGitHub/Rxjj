package com.example.r.rxjj.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.r.rxjj.R;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaFixRetrofitActivity extends AppCompatActivity {
    public static final String RR = "Rx模拟网络轮询";
    //模拟轮询服务器次数
    private int req = 0;
    private Button mBt;
    private StringBuffer mStringBuffer = new StringBuffer();
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_fix_retrofit);
        mBt = (Button) findViewById(R.id.bt);
        mTv = (TextView) findViewById(R.id.tv);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toReq();
            }
        });
    }

    private void toReq() {//创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pretest.wayaa.com/crm-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建网络接口实例
        RetrofitInter retrofitInter = retrofit.create(RetrofitInter.class);
        //把此网络请求封装成 被观察者 obsevable
        Observable<BaseEntity<UserInfo>> observable = retrofitInter.getCall();
        //发送网络请求 通过repeatWhen 进行请求
        observable.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，此处使用flatMap操作符接收上游的数据
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable，即轮询
                // 此处有2种情况：
                // 1. 若返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable，即轮询结束
                // 2. 若返回其余事件，则重新订阅 & 发送原来的 Observable，即继续轮询

                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {
                        if (req>5){
                            return Observable.error(new Throwable("连接超时..."));
                        }
                        return Observable.just(1).delay(2000, TimeUnit.MILLISECONDS);
                    }
                });
            }
        }).subscribeOn(Schedulers.io())      //IO线程执行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //主线程处理结果
                    .subscribe(new Observer<BaseEntity<UserInfo>>() {
                        private String mString;

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseEntity<UserInfo> value) {
                            req++;
                            mString = new Gson().toJson(value);
                            Log.e(RR,mString);
                            System.out.println(mString);
                            appendLog(mString);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

    }
    private void appendLog(String string) {
        mStringBuffer.append(string+"\n");
        mTv.setText(mStringBuffer);
    }
}
