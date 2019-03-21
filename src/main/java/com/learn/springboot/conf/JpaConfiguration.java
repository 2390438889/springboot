package com.learn.springboot.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hearts
 * @date 2019/3/19
 * @desc jpa配置类
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
/**
 * Spring通知执行顺序
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
/**
 * 启用事务管理自动化配置
 */
@EnableJpaRepositories(basePackages = "com.**.dao")
/**
 * jpaRepository 接口所在的路径
 */
@EntityScan(basePackages = "com.**.pojo")
/**
 *实体类所在的路径
 */
public class JpaConfiguration {

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
