package com.jethro.utils.response;



import java.util.Date;

/**
 * @author Jethro
 * Created on 2019/5/6
 * description:
 */
public class ErrorResponseVO extends ResponseVO {
    String path;
    String error;
    Date timestamp;
    private String exceptionClazz;

    public ErrorResponseVO() {
        this.timestamp = new Date();
    }

    public ErrorResponseVO(ErrorCode errorCode, String message) {
        this.timestamp = new Date();
        this.setCode(errorCode.getCode().intValue());
        this.setMessage(message != null ? message : errorCode.getMessage());
        this.setError(errorCode.toString());
    }

    public ErrorResponseVO(ErrorCode errorCode) {
        this((ErrorCode) errorCode, (String) null);
    }

    public ErrorResponseVO setCode(int code) {
        super.setCode(code);
        return this;
    }

    public ErrorResponseVO setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    public ErrorResponseVO setDetailMsg(String detailMsg) {
        super.setDetailMsg(detailMsg);
        return this;
    }

    public ErrorResponseVO setVersion(String version) {
        super.setVersion(version);
        return this;
    }

    public ErrorResponseVO(String path, String error, Date timestamp, String exceptionClazz) {
        this.timestamp = new Date();
        this.path = path;
        this.error = error;
        this.timestamp = timestamp;
        this.exceptionClazz = exceptionClazz;
    }

    public ErrorResponseVO(int code, String message, String detailMsg, Object data, String path, String error, Date timestamp, String exceptionClazz) {
        super(code, message, detailMsg, data);
        this.timestamp = new Date();
        this.path = path;
        this.error = error;
        this.timestamp = timestamp;
        this.exceptionClazz = exceptionClazz;
    }

    public String getPath() {
        return this.path;
    }

    public ErrorResponseVO setPath(String path) {
        this.path = path;
        return this;
    }

    public String getError() {
        return this.error;
    }

    public ErrorResponseVO setError(String error) {
        this.error = error;
        return this;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public ErrorResponseVO setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getExceptionClazz() {
        return this.exceptionClazz;
    }

    public ErrorResponseVO setExceptionClazz(String exceptionClazz) {
        this.exceptionClazz = exceptionClazz;
        return this;
    }

    public ErrorResponseVO(Integer code, String message) {
        super(code.intValue(), message, message, (Object) null);
        this.timestamp = new Date();
    }

    public ErrorResponseVO(Integer code, String message, Object object) {
        super(code.intValue(), message, message, object);
        this.timestamp = new Date();
    }

    public static ErrorResponseVO build() {
        return new ErrorResponseVO();
    }
}