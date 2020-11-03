package com.xiaolu.mylibrary.rxbus;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据总线(半成品)
 */
public class RxBus {
    //订阅者集合
    private Set<Object> subscribers;

    /**
     * 注册
     */
    public synchronized void register(Object subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * 取消注册
     */
    public synchronized void unRegister(Object subscriber) {
        subscribers.remove(subscriber);
    }

    //带线程安全的单例
    private static volatile RxBus instance;

    private RxBus() {
        //带读写分离的集合
        subscribers = new CopyOnWriteArraySet<>();
    }

    public static synchronized RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * 把处理过程包装起来
     * 对外提供一个function接口
     */
    public void chainProcess(Function function, Class aClass) {
        Observable.just(aClass)  //emitter.onNext();
                .map(function)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    if (o == null) {
                        return;
                    }
                    //发送数据到presenter类中
                    send(o, aClass);
                });
    }

    public void send(Object data, Class aClass) {
        for (Object subscriber : subscribers) {
            callMethodByAnnotation(subscriber, data, aClass);
        }
    }

    private void callMethodByAnnotation(Object target, Object data, Class aClass) {
        try {
            //1.得到订阅该bus的对象它身上的所有的方法
            Method[] methodArray = target.getClass().getDeclaredMethods();
            for (Method method : methodArray) {
                if (null != method.getAnnotation(RegisterRxBus.class)) {
                    //就去执行对应的方法
                    Class value = method.getAnnotation(RegisterRxBus.class).value();
                    if (value == aClass) {
                        method.invoke(target, new Object[]{data});
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
