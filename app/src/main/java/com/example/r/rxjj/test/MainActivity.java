package com.example.r.rxjj.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r.rxjj.R;
import com.example.r.rxjj.rc.BaseRecyclerAdapter;
import com.example.r.rxjj.rc.SmartViewHolder;

import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="Rx";
    private RecyclerView mRc;
    private BaseRecyclerAdapter mBaseRecyclerAdapter;
    private String[] interfaceRx={"网络请求超时","模拟网络请求轮询","网络请求嵌套回调","dataBinding"};
    private Void[] mVoids=new Void[2];
    private TextView mTt;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mContext=this;
        mRc = (RecyclerView) findViewById(R.id.rc);
        mTt = (TextView) findViewById(R.id.tt);
        mTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBaseRecyclerAdapter.getItemCount()>=interfaceRx.length) {
                    Toast.makeText(mContext,"没有更多了~",Toast.LENGTH_SHORT).show();
                }else {
                    mBaseRecyclerAdapter.loadmore(initData(2));
                }
            }
        });
        mRc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<Void>(R.layout.item_rc) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Void model, int position) {
                holder.text(R.id.item_tv,interfaceRx[position]);
            }
        };
        mBaseRecyclerAdapter.refresh(initData(interfaceRx.length));
        mRc.setAdapter(mBaseRecyclerAdapter);
        mBaseRecyclerAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (interfaceRx[i]) {
                    case "网络请求超时":
                        toBanner(RxAndRetrofitActivity.class);
                        break;
                    case "模拟网络请求轮询":
                        toBanner(RxJavaFixRetrofitActivity.class);
                        break;
                    case "网络请求嵌套回调":
                        toBanner(RxRetrofitCallBackActivity.class);
                        break;
                    case "dataBinding":
                        toBanner(DataBindingActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
//        //被观察者
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                //创建事件
//                    e.onNext(1);
//                    e.onNext(2);
//                    e.onNext(3);
//                    e.onComplete();
//                    e.onNext(4);
//                    e.onNext(5);
//                    e.onNext(6);
//            }
//
//        });
//        //创建观察者
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                //发生了订阅连接调用这个  默认最先被调用  优先级比较高
//                Log.e(TAG,"onSubscribe");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                //接收被观察者的单发事件
//                Log.e(TAG,"onNext"+value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                //发生错误的时候调用  调用的时候观察者将不会再接收事件 但被观察者可以继续发送事件
//                // 与onComplete二者只会执行一个
//                Log.e(TAG,"onError");
//            }
//
//            @Override
//            public void onComplete() {
//                //同上
//                Log.e(TAG,"onComplete");
//            }
//        };
//        //创建观察者的方法二
//        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
//
//            @Override
//            public void onSubscribe(Subscription s) {
//                //发生了订阅连接调用这个  默认最先被调用  优先级比较高
//                Log.e(TAG,"onSubscribe");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                //接收被观察者的单发事件
//                Log.e(TAG,"onNext"+value);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                //发生错误的时候调用  调用的时候观察者将不会再接收事件 但被观察者可以继续发送事件
//                // 与onComplete二者只会执行一个
//                Log.e(TAG,"onError");
//            }
//
//            @Override
//            public void onComplete() {
//                //同上
//                Log.e(TAG,"onComplete");
//            }
//
//        };
//        //创建了被观察者和观察者就可以进行事件的订阅了
//        observable.subscribe(observer);
    }

    private Collection<Void> initData(int length) {
        Void[] voids = new Void[length];
        return Arrays.asList(voids);
    }

    private void toBanner(Class s) {
        startActivity(new Intent(this,s));
    }

}
