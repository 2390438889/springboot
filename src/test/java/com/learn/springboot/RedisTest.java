package com.learn.springboot;

import com.learn.springboot.pojo.Department;
import com.learn.springboot.pojo.Role;
import com.learn.springboot.pojo.User;
import com.learn.springboot.redis.UserRedis;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private UserRedis userRedis;

    @Before
    public void before() {
        Department department = new Department();
        department.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        User user = new User();
        user.setName("user");
        user.setCreateDate(new Date());
        user.setDepartment(department);
        user.setRoles(Arrays.asList(role));

        userRedis.delete(this.getClass() + ":userByname:" + user.getName());
        userRedis.add(this.getClass() + ":userByname:" + user.getName(), 10L, user);
        System.out.println(this.getClass() + ":userByname:" + user.getName());
    }

    @Test
    public void get() {
        User user = userRedis.get(this.getClass() + ":userByname:user");
        Assert.assertNotNull(user);
        System.out.println(user);
    }


}
