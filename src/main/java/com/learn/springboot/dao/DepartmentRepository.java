package com.learn.springboot.dao;

import com.learn.springboot.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hearts
 * @date 2019/3/19
 * @desc 部门数据层
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
