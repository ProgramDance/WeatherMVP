package com.demo.weathermvp.model;

public interface GetRemoteListener<T> {
    void onSuccess(T t);
    void onError(String msg);
}
