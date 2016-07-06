package com.flow.common.exception;

import com.flow.common.constants.ResponseStatus;

public class GenericBusinessException extends RuntimeException{

	private static final long serialVersionUID = -5800704893659570945L;

	//错误状态码
    int code;

    //响应状态码  对应http response status code
    int responseStatus = 500;

   public GenericBusinessException(String message){
       super(message);
   }

    public GenericBusinessException(int code , String message){
        super(message);
        this.code = code;
    }

    public GenericBusinessException(int code,int responseStatus , String message){
        super(message);
        this.code = code;
        this.responseStatus = responseStatus;
    }

    public GenericBusinessException(ResponseStatus responseStatus){
        super(responseStatus.chineseMessage);
        this.code = responseStatus.code;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +",\"message\":\""+getMessage()+"\","+
                "\"responseStatus\":"+responseStatus+
                '}';
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
