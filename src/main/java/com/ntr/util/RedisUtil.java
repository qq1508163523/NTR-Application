package com.ntr.util;

import com.ntr.exception.CommonException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private final RedisTemplate<String,Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            throw new CommonException("SERVER错误");
        }
    }

    public boolean hasKey(String key){
        Boolean res = redisTemplate.hasKey(key);
        return res != null?res:false;
    }

    public void setIfPresent(String key, Object value,Long second){
        try{
            redisTemplate.opsForValue().setIfPresent(key,value,second, TimeUnit.SECONDS);
        }catch(Exception e){
            throw new CommonException("SERVER错误");
        }
    }

    public void setIfAbsent(String key, Object value,Long second){
        try{
            redisTemplate.opsForValue().setIfAbsent(key,value,second, TimeUnit.SECONDS);
        }catch(Exception e){
            throw new CommonException("SERVER错误");
        }
    }

    public void delete(String key){
        try{
            redisTemplate.delete(key);
        }catch(Exception e){
            throw new CommonException("SERVER错误");
        }
    }
}
