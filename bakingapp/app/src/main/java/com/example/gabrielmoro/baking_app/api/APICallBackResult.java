package com.example.gabrielmoro.baking_app.api;

/**
 * This callback is used to catch the result state according to the server response.
 *
 * @param <T> defines some object
 */
public interface APICallBackResult<T> {

    void onSucess(T result);

    void onFailure(Throwable problem);

    void onCompleted();
}
