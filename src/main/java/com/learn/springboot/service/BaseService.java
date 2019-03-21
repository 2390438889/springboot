package com.learn.springboot.service;

import com.learn.springboot.redis.RedisRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc
 */
public class BaseService<T> {

    private final JpaRepository<T, Long> jpaRepository;

    private final RedisRepository<T> redisRepository;

    private final String keyHead;

    public BaseService(JpaRepository jpaRepository, RedisRepository redisRepository, String keyHead) {
        this.jpaRepository = jpaRepository;
        this.redisRepository = redisRepository;
        this.keyHead = keyHead;
    }

    public T findById(Long id) {
        T t = redisRepository.get(keyHead + id);
        if (t == null) {
            t = jpaRepository.findById(id).get();
            if (t != null) {
                redisRepository.add(keyHead + id, 30L, t);
            }
        }
        return t;
    }

    public T create(T t) {
        T newT = jpaRepository.save(t);
        if (newT != null) {

            redisRepository.add(getKey(t), 30L, t);
        }

        return newT;
    }

    private String getKey(T t) {
        String key = "";
        try {
            Method method = redisRepository.getClazz().getMethod("getId", null);
            key = keyHead + method.invoke(t, null);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return key;
    }

    public T update(T t) {
        if (t != null) {
            redisRepository.delete(getKey(t));
            redisRepository.add(getKey(t), 30L, t);
        }
        return jpaRepository.save(t);
    }

    public void delete(T t) {
        redisRepository.delete(getKey(t));
        jpaRepository.delete(t);
    }
}
