package com.yibo.feign.domain;

import java.io.Serializable;

/**
 * @author: huangyibo
 * @Date: 2018/12/22 19:55
 * @Description:
 */
public class Person implements Serializable {

    private Long id;

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
