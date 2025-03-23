package com.daocao.common.enums;

import com.daocao.common.constants.HttpStatus;

/**
 * @Project: daocao
 * @Description: 响应状态码枚举类
 * @Author: bf
 * @Date: 2025/3/23 08:15:11
 * @Version: 1.0
 */
public enum HttpStatusEnum {

    SUCCESS(HttpStatus.SUCCESS, "操作成功"),
    CREATED(HttpStatus.CREATED, "对象创建成功"),
    ACCEPTED(HttpStatus.ACCEPTED, "请求已经被接受"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "操作已经执行成功，但是没有返回数据"),
    MOVED_PERM(HttpStatus.MOVED_PERM, "资源已被移除"),
    SEE_OTHER(HttpStatus.SEE_OTHER, "重定向"),
    NOT_MODIFIED(HttpStatus.NOT_MODIFIED, "资源没有被修改"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "参数列表错误（缺少，格式不匹配）"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "未授权"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "禁止访问"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "请求地址不存在"),

    BAD_METHOD(HttpStatus.BAD_METHOD, "不支持的HTTP请求方法"),
    CONFLICT(HttpStatus.CONFLICT, "资源冲突，请重新尝试"),
    UNSUPPORTED_TYPE(HttpStatus.UNSUPPORTED_TYPE, "不支持的数据类型"),

    ERROR(HttpStatus.ERROR, "服务器内部错误"),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "不支持的接口"),
    WARN(HttpStatus.WARN, "警告"),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "用户名或密码错误"),
    UNAUTHORIZED_ACCOUNT(HttpStatus.UNAUTHORIZED, "账号被冻结");

    private Integer statusCode;
    private String statusMsg;

    private HttpStatusEnum(Integer statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
