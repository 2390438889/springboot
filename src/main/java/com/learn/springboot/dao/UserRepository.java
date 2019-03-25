package com.learn.springboot.dao;

import com.learn.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * @author Hearts
 * @date 2019/3/19
 * @desc
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
