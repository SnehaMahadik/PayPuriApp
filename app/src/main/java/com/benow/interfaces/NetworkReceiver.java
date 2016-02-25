package com.benow.interfaces;


public interface NetworkReceiver {

    /**
     * @param obj : Object of Pojo class
     * @param tag : Request id Which is used to initiate volley request.
     */
    <T> void onResponse(T obj, int tag);

    /**
     * @param error_code : Returns error code.
     */
    void onError(int error_code, int tag, Object object);
}
