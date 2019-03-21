package com.learn.springboot;

import com.learn.springboot.dao.DepartmentRepository;
import com.learn.springboot.dao.RoleRepository;
import com.learn.springboot.dao.UserRepository;
import com.learn.springboot.pojo.Department;
import com.learn.springboot.pojo.Role;
import com.learn.springboot.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Hearts
 * @date 2019/3/19
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void before() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department depar = new Department();
        depar.setName("开发部");
        departmentRepository.save(depar);
        Assert.assertNotNull(depar.getId());


        Role role = new Role();
        role.setName("管理员");
        roleRepository.save(role);
        Assert.assertNotNull(role.getId());


        User user = new User();
        user.setName("user");
        user.setCreateDate(new Date());
        user.setDepartment(depar);
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findPage() {
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));

        Page<User> page = userRepository.findAll(pageable);

        Assert.assertNotNull(page);

        page.stream().forEach(System.out::println);


    }
}
