package com.learn.springboot.redis;

import com.learn.springboot.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc
 */
@Repository
public class UserRedis extends RedisRepository<User> {
    public UserRedis() {
        super(User.class);
    }
}
