package com.bf.common.response;

import cn.hutool.core.util.ObjectUtil;
import com.bf.common.constants.HttpStatus;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Project: straw_fast_dev
 * @Description: 统一返回结果集
 * @Author: bf
 * @Date: 2025/1/16 23:05:822
 * @Version: 1.0
 */
public class StrawResult  extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";


    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public StrawResult(){

    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * @param code 状态码
     * @param msg 返回内容
     */
    public StrawResult(int code, String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public StrawResult(int code, String msg, Object data){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (ObjectUtil.isNotNull(data)){
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static StrawResult success() {
        return StrawResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static StrawResult success(Object data) {
        return StrawResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static StrawResult success(String msg) {
        return StrawResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static StrawResult success(String msg, Object data) {
        return new StrawResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static StrawResult warn(String msg) {
        return StrawResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static StrawResult warn(String msg, Object data) {
        return new StrawResult(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static StrawResult error() {
        return StrawResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static StrawResult error(String msg) {
        return StrawResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static StrawResult error(String msg, Object data) {
        return new StrawResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static StrawResult error(int code, String msg) {
        return new StrawResult(code, msg, null);
    }

    /**
     * 是否为成功消息
     *
     * @return 结果
     */
    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 是否为警告消息
     *
     * @return 结果
     */
    public boolean isWarn() {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * 是否为错误消息
     *
     * @return 结果
     */
    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }


   /**
    * @Description: 方便链式调用
    * @param key 键
    * @param value 值
    * @return StrawResult
    * @Author: bf
    * @Date: 2025/1/16 23:03
    */
    @Override
    public StrawResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
