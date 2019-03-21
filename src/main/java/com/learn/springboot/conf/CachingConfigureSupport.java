package com.learn.springboot.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc 结构简单的对象，即没有包含其他对象的尸体，可以使用SpringCache注解的方式来使用Redis缓存，
 * 要使用注解的方式调用缓存，必须在配置类中启用SpringCache
 */
@Configuration
@EnableCaching
public class CachingConfigureSupport {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //redis缓存配置 有效时间为12小时
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(12));


        return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    /**
     * key生成规则
     */
    @Bean
    public KeyGenerator simpleKey() {
        return new KeyGenerator() {

            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName() + ":");
                for (Object object : objects) {
                    sb.append(object.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public KeyGenerator objectId() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName() + ":");
                try {
                    sb.append(objects[0].getClass().getMethod("getId", null)
                            .invoke(objects[0], null).toString());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return sb.toString();
            }

        };
    }
}
