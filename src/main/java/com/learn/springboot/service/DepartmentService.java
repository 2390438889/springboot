package com.learn.springboot.service;

import com.learn.springboot.dao.DepartmentRepository;
import com.learn.springboot.pojo.Department;
import com.learn.springboot.redis.DepartmentRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc 自动增加缓存的创建，修改和删除等功能，
 * @CacheEvict 为删除缓存，当删除数据时，如果缓存还在，就必须删除
 * @CachePut 为修改缓存，不存在则创建
 * @Cacheable 为存取缓存
 * 各个注解中的value参数是一个key的前缀，
 * 并由keyGenerator按一定的规则生成的一个唯一的标识
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentRedis departmentRedis;

    @Cacheable(value = "mysql:findById:department", keyGenerator = "simpleKey")
    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @CachePut(value = "mysql:findById:department", keyGenerator = "objectId")
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @CachePut(value = "mysql:findById:department", keyGenerator = "objectId")
    public Department update(Department department) {
        return departmentRepository.save(department);
    }

    @CacheEvict(value = "mysql:findById:department", keyGenerator = "simpleKey")
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

}
