package com.yibo.feign.service.provider.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: huangyibo
 * @Date: 2019/1/4 23:34
 * @Description:
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    private String name;

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
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
