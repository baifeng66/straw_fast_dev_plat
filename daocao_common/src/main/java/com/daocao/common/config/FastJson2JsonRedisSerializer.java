package com.daocao.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @Project: daocao
 * @Description: FastJson2JsonRedisSerializer.java 序列化器
 * @Author: bf
 * @Date: 2025/3/25 09:33:43
 * @Version: 1.0
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }
    
    @Override
    public byte[] serialize(T value) throws SerializationException {
        if (ObjectUtil.isEmpty(value)) {
            return new byte[0];
        }
        // 将指定对象序列化为json字符串
        return JSON.toJSONString(value, JSONWriter.Feature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    /**
     * @Description: 反序列化
     * @param bytes
     * @return T
     * @throws
     * @Author: bf
     * @Date: 2025/3/25 09:40
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (ObjectUtil.isEmpty(bytes) || bytes.length <= 0) {
            return null;
        }
        // 将json字符串解析为t。如果接收到的字符串为空或空，则返回空。
        return JSON.parseObject(new String(bytes, DEFAULT_CHARSET), clazz, JSONReader.Feature.SupportAutoType);
    }
}
