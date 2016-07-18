package com.esyto.myprostruct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        test();
    }
    private void test(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Observable.just(list).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {

            }
        });
        Observable.from(list).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Logger.e("--------onCompleted "+ "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.e("--------onNext "+"s : " + s);
            }
        });
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("test");
                subscriber.onCompleted();
            }
        })
        .subscribeOn(Schedulers.io())//事件处理线程
        .observeOn(AndroidSchedulers.mainThread())//事件响应线程
        .throttleFirst(500, TimeUnit.MILLISECONDS)//在每次事件触发后的一定时间间隔内丢弃新的事件
        .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.e("--------onNext "+"s : " + s);
            }
        });

        //变换

    }
}
