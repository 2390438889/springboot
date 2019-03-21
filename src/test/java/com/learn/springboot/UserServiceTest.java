package com.learn.springboot;

import com.learn.springboot.pojo.Department;
import com.learn.springboot.pojo.User;
import com.learn.springboot.service.DepartmentService;
import com.learn.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Hearts
 * @date 2019/3/21
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void create() {
        Department department = new Department();
        department.setName("移动部");
        departmentService.create(department);

        User user = new User();
        user.setId(10L);
        user.setName("里斯");
        user.setDepartment(department);
        user.setCreateDate(new Date());

        userService.update(user);
    }
}
