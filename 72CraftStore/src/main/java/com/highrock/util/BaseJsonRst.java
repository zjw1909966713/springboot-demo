package com.highrock.util;

/**
 *此类用于返回结构
 * @author zhangjinwen
 * @create 2017-11-01 11:05
 * @desc
 **/
public class BaseJsonRst<T> {
    private boolean success;	//成功或失败的标志
    private String message;	//成功或失败的信息
    private T result;	//结果集

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static BaseJsonRst success(){
        BaseJsonRst ret=new BaseJsonRst();
        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }
    public static BaseJsonRst success(Object o){
        BaseJsonRst ret=new BaseJsonRst();
        ret.setSuccess(true);
        ret.setMessage("success");
        ret.setResult(o);
        return ret;
    }
    public static BaseJsonRst error(String message){
        BaseJsonRst ret=new BaseJsonRst();
        ret.setSuccess(false);
        ret.setMessage(message);
        return ret;
    }


}
