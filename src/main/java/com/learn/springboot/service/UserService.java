package com.learn.springboot.service;

import com.learn.springboot.dao.UserRepository;
import com.learn.springboot.pojo.User;
import com.learn.springboot.redis.UserRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hearts
 * @date 2019/3/21
 * @desc
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    public UserService(UserRepository jpaRepository, UserRedis redisRepository) {
        super(jpaRepository, redisRepository, "mysql:get:user");
    }
}
