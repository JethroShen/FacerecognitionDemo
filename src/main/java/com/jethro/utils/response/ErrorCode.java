package com.jethro.utils.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.Map;

/**
 * @author Jethro
 * Created on 2019/5/6
 * description:
 */
public enum ErrorCode {
    RESPONSE_SUCCESS(200, "操作成功", "操作成功"),
    BAD_REQUEST(400, "服务器无法理解请求的格式，客户端不应当尝试再次使用相同的内容发起请求", "服务器无法理解请求的格式，客户端不应当尝试再次使用相同的内容发起请求"),
    ACCESS_DENIED(403, "您暂无权限访问", "您暂无权限访问"),
    NOT_FOUND(404, "找不到与URI相匹配的资源", "找不到与URI相匹配的资源"),
    METHOD_NOT_ALLOWED(405, "请求方式错误", "请求方式错误，请使用GET/POST方式请求"),
    DUPLICATE_DATA_PROCESSING(423, "当前记录已被处理", "当前记录已被处理"),
    INTERNAL_PROGRAM_ERROR(500, "程序内部错误，操作失败", "程序内部错误，操作失败"),
    SERVICE_UNAVAILABLE(503, "服务不可用", "服务不可用"),
    MICRO_SERVICE_UNAVAILABLE(5031, "微服务不可用", "微服务不可用"),
    SERVICE_REQUEST_TIME_OUT(504, "服务请求超时", "服务请求超时"),
    REQUEST_TIME_OUT(504, "服务器请求超时", "服务器请求超时"),
    RESPONSE_CODE_PARAM_FORMAT_ERROR(5001, "请求失败、参数格式错误", "请求失败、参数格式错误"),
    RESPONSE_CODE_PARAM_ERROR(5003, "参数错误", "参数错误"),
    RESPONSE_CODE_REQ_CANNOT_EMPTY(5004, "必要的请求参数不能为空", "必要的请求参数不能为空"),
    DataAccessException(5011, "数据库操作失败", "数据库操作失败"),
    ConstraintViolationException(5012, "对象已经存在，请勿重复操作", "对象已经存在，请勿重复操作"),
    SERVER_UNAVAILABLE(5013, "服务不可用", "服务不可用"),
    NullException(5015, "调用了未经初始化的对象或者是不存在的对象", "调用了未经初始化的对象或者是不存在的对象"),
    ClassNotFoundException(5017, "指定的类不存在", "指定的类不存在"),
    ArithmeticException(5018, "数学运算异常", "数学运算异常"),
    ArrayIndexOutOfBoundsException(5019, "数组下标越界", "数组下标越界");

    private int code;
    private String message;
    private String detailMsg;

    private ErrorCode(int code, String message, String detailMsg) {
        this.code = code;
        this.message = message;
        this.detailMsg = detailMsg;
    }

    public Integer getCode() {
        return Integer.valueOf(this.code);
    }

    public void setCode(int status) {
        this.code = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMsg() {
        return this.detailMsg;
    }

    public ErrorCode setDetailMsg(String msg, String detailMsg) {
        this.detailMsg = detailMsg;
        return this;
    }

    public static ResponseVO buildEnumResponseVO(ErrorCode vitalResponseCode, Object data) {
        return new ResponseVO(vitalResponseCode.getCode().intValue(), vitalResponseCode.getMessage(), (String)null, data);
    }

    public static ResponseVO buildEnumResponseVO(ErrorCode vitalResponseCode, String detailMsg, Object data) {
        return new ResponseVO(vitalResponseCode.getCode().intValue(), vitalResponseCode.getMessage(), detailMsg, data);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public Map<String, Object> toMap() {
        return (Map) JSONObject.parseObject(this.toString(), new TypeReference<Map<String, Object>>() {
        }, new Feature[0]);
    }

    public JSONObject toJSON() {
        return (JSONObject)JSONObject.toJSON(this);
    }
}

