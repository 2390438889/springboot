package com.learn.springboot.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Hearts
 * @date 2019/3/19
 * @desc
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {
    /**
     * @Id 定义一条记录的唯一标识
     */
    @Id
    /**
     * JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO. 
     * TABLE：使用一个特定的数据库表格来保存主键。 
     * EQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。 
     * IDENTITY：主键由数据库自动生成（主要是自动增长型） 
     * AUTO：主键由程序控制。 
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
