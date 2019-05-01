package com.yibo.springbootbeanvalidation.domain;

import com.yibo.springbootbeanvalidation.validation.constraints.ValidCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author: huangyibo
 * @Date: 2018/12/9 17:50
 * @Description:
 */
public class User {

    @Max(value = 10000)
    private long id;

    @NotNull
    //@NonNull
    //@NotNull
    private String name;

    //卡号 YIBO-123456789
    @NotNull
    @ValidCardNumber
    private String cardNumber;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
