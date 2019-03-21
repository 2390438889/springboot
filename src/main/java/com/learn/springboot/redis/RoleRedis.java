package com.learn.springboot.redis;

import com.learn.springboot.pojo.Role;
import org.springframework.stereotype.Repository;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc
 */
@Repository
public class RoleRedis extends RedisRepository<Role> {
    public RoleRedis() {
        super(Role.class);
    }
}
