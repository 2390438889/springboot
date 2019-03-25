package com.learn.springboot.service;

import com.learn.springboot.dao.RoleRepository;
import com.learn.springboot.pojo.Role;
import com.learn.springboot.redis.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Hearts
 * @date 2019/3/21
 * @desc
 */
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    public RoleService(RoleRepository jpaRepository, RedisRepository redisRepository) {
        super(jpaRepository, redisRepository, "mysql:get:roles");
    }
}
