package com.shop.demo.utils;

public class AjaxResult<T> {

    private static final int CODE_SUCCESS = 200;

    private static final int CODE_FAIL = 400;

    private static final String MSG_SUCCESS = "success";
    private static final String MSG_FAIL = "failed";

    private int code;
    private T data;
    private String msg;

    public AjaxResult() {

    }

    public AjaxResult(int code) {
        this.code = code;
    }

    public AjaxResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static AjaxResult success() {
        return new AjaxResult(CODE_SUCCESS, MSG_SUCCESS);
    }

    public static AjaxResult success(Object data) {
        return new AjaxResult(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static AjaxResult fail() {
        return new AjaxResult(CODE_FAIL, MSG_FAIL);
    }

    public static AjaxResult error(String msg) {
        return new AjaxResult(CODE_FAIL, msg);
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
