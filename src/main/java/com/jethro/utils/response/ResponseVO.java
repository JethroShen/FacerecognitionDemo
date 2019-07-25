package com.jethro.utils.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: 沈佳俊
 * @CreateTime: 2019-05-05
 * @Description: ${Description}
 */
public class ResponseVO<T> implements Serializable {
    private static final long serialVersionUID = 8051203182511793143L;
    public static final String VERSION = "v1.0";
    @ApiModelProperty(
            value = "状态码",
            name = "code",
            example = "200"
    )
    private int code = 200;
    @ApiModelProperty(
            value = "接口执行状态消息",
            name = "message",
            example = "成功！"
    )
    private String message = "操作成功!";

    @ApiModelProperty(
            value = "请求执行失败的详细信息",
            name = "detailMsg",
            example = "NullPointException"
    )
    private String detailMsg;

    @ApiModelProperty(
            value = "版本号",
            name = "version",
            example = "v1.0"
    )
    public String version = "v1.0";

    @ApiModelProperty(
            value = "请求返回消息对象",
            name = "data",
            example = "User.class"
    )
    private T data;

    public static String getVERSION() {
        return "v1.0";
    }

    public int getCode() {
        return this.code;
    }

    public ResponseVO setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ResponseVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetailMsg() {
        return this.detailMsg;
    }

    public ResponseVO setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public ResponseVO setVersion(String version) {
        this.version = version;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ResponseVO setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseVO() {
    }

    public ResponseVO(int code, String message, String detailMsg, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.detailMsg = detailMsg;
    }

    public static ResponseVO build() {
        return new ResponseVO();
    }
}

