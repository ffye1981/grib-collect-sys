package com.zgss.grib.response;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {
    //状态妈
    private int code = 0;
    //错误信息
    String message = "请求成功";
    //返回数据
    private T data;

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult() {
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "ApiResult{code='" + this.code + '\'' + ", message='" + this.message + '\'' + ", data=" + this.getData() + '}';
    }
}
