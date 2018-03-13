package com.example.r.rxjj.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.r.rxjj.R;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
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

/**
 * RxJava 网络请求出错重连（结合Retrofit）
 */
public class RxAndRetrofitActivity extends AppCompatActivity {
    private Button mBtRx;
    private TextView mTv;
    public static final String TT="RxR";
    // 设置变量
    // 可重试次数
    private int maxConnectCount = 10;
    // 当前已重试次数
    private int currentRetryCount = 0;
    // 重试等待时间
    private int waitRetryTime = 0;
    private StringBuffer mStringBuffer = new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_and_retrofit);
        mBtRx = (Button) findViewById(R.id.bt_rx);
        mTv = (TextView) findViewById(R.id.tv);
        mBtRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                implementRxR();
            }
        });
    }

    private void implementRxR() {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://pretest.wayaa.com/crm-api/")//网络请求的地址
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
        RetrofitInter retrofitInter = retrofit.create(RetrofitInter.class);
        Observable<BaseEntity<UserInfo>> observable = retrofitInter.getCall();
        //发送网络请求 & 通过retryWhen（）进行重试
        //注：主要异常才会回调retryWhen（）进行重试
        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        Log.d(TT,"发生异常 = "+throwable.toString());
                        appendLog("发生异常 = "+throwable.toString());
                        /**
                         * 需求1：根据异常类型选择是否重试
                         * 即，当发生的异常 = 网络异常 = IO异常 才选择重试
                         */
                        if (throwable instanceof IOException){
                            Log.e(TT,"IO异常");
                            appendLog("IO异常");
                            /**
                             * 需求2：限制重试次数
                             * 即，当已重试次数 < 设置的重试次数，才选择重试
                             */
                            if (maxConnectCount>currentRetryCount) {
                                //记录重试记录
                                currentRetryCount++;
                                Log.d(TT,"重试次数+"+currentRetryCount);
                                appendLog("重试次数+"+currentRetryCount);
                                /**
                                 * 需求2：实现重试
                                 * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
                                 *
                                 * 需求3：延迟1段时间再重试
                                 * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
                                 *
                                 * 需求4：遇到的异常越多，时间越长
                                 * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
                                 */
                                //设置等待时间
                                waitRetryTime = 1000+currentRetryCount*1000;
                                Log.d(TT,"重试时间="+waitRetryTime);
                                appendLog("重试时间="+waitRetryTime);
                                return Observable.just(1).delay(waitRetryTime, TimeUnit.MILLISECONDS);
                            }else {
                                // 若重试次数已 > 设置重试次数，则不重试
                                // 通过发送error来停止重试（可在观察者的onError（）中获取信息）
                                return Observable.error(new Throwable("重试次数超过+"+maxConnectCount+"即"+currentRetryCount+"不再重试"));
                            }
                        }else {
                            //这里是不属于IO的异常
                            return Observable.error(new Throwable("非IO异常"));
                        }
                    }
                });
            }
        }).subscribeOn(Schedulers.io())  //切换到IO线程进行网络访问
            .observeOn(AndroidSchedulers.mainThread()) //切换到主线程处理结果
            .subscribe(new Observer<BaseEntity<UserInfo>>() {
                private String mString;

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BaseEntity<UserInfo> value) {
                    System.out.println("接收了服务器的返回数据成功!"+value.toString());
                    appendLog("接收了服务器的返回数据成功!"+value.toString());
                    mString = new Gson().toJson(value).toString();
                    Log.e(TT,"数据详情 = "+mString);
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TT,"获取重试的信息停止"+e.toString());
                    appendLog("获取重试的信息停止"+e.toString());
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
