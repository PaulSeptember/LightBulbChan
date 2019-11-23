package com.light.bulb.chan.lightbulbchan.models;

public class Response<T> {
    private T value;
    private boolean status;

    public Response(T value, boolean status) {
        this.value = value;
        this.status = status;
    }

    public T getValue() {
        return value;
    }

    public Response<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Response<T> setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
