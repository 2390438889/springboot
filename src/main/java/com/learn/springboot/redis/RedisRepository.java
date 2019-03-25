package com.learn.springboot.redis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Hearts
 * @date 2019/3/19
 * @desc 用户的redis服务类
 */
@Repository
public class RedisRepository<T> {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Class<T> clazz;

    public RedisRepository() {
    }

    public RedisRepository(Class<T> clazz) {
        this.clazz = clazz;
    }


    public void add(String key, Long time, T object) {
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(object), time, TimeUnit.MINUTES);
    }

    public void add(String key, Long time, List<T> users) {
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(users), time, TimeUnit.MINUTES);
    }

    public T get(String key) {
        return get(key, clazz, clazz);
    }

    public List<T> getList(String key) {
        return get(key, new TypeToken<List<T>>() {
        }.getType(), List.class);
    }

    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    private <T, U> T get(String key, U u, Class<T> t) {
        Gson gson = new Gson();
        T users = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(userJson)) {
            users = gson.fromJson(userJson, (Type) u);
        }
        return users;
    }

    public Class<T> getClazz() {
        return clazz;
    }
}
