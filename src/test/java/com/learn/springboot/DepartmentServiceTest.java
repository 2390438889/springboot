package com.learn.springboot;

import com.learn.springboot.pojo.Department;
import com.learn.springboot.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void create() {
        Department department = new Department();
        department.setName("测试部");
        departmentService.create(department);
        System.out.println(department);
    }

    @Test
    public void find() {
        System.out.println(departmentService.findById(13L));
    }

}
