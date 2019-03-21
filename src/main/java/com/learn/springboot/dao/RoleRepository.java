package com.learn.springboot.dao;

import com.learn.springboot.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hearts
 * @date 2019/3/19
 * @desc 权限数据层
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
