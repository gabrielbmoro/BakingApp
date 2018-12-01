package com.example.gabrielmoro.baking_app.api;

public interface APICallBackResult<T> {

    void onSucess(T result);

    void onFailure(Throwable problem);

    void onCompleted();
}
