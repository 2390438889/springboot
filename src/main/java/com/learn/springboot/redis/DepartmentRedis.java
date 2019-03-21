package com.learn.springboot.redis;

import com.learn.springboot.pojo.Department;
import org.springframework.stereotype.Repository;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc
 */
@Repository
public class DepartmentRedis extends RedisRepository<Department> {
    public DepartmentRedis() {
        super(Department.class);
    }
}
