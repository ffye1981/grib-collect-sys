package com.zgss.grib.response;

public enum ResultEnum {

    SUCCESS(0, "请求成功"),
    ERROR(1, "系统异常，请求错误"),
    TOKEN_LOSE(2, "token失效，当前会话已过期，需要重新登录"),
    NUKONW_USER_ERROR(3, "用户不存在"),
    PASS_USER_ERROR(4, "密码不正确");

    private Integer code;
    private String msg;

    private ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }
}
