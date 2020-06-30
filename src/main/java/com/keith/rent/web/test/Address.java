package com.keith.rent.web.test;

import cn.gjing.tools.common.util.BeanUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Data
public class Address implements Serializable {

    private static final long serialVersionUUID = 1L;

    String street;
    String country;


    @Override
    public String toString() {
        return new StringBuffer("Street:")
                .append(this.street)
                .append("Country:")
                .append(this.country).toString();
    }

    public Address(String street, String country) {
        this.street = street;
        this.country = country;
    }

    public Address() {
    }

    public static void main(String[] args) {
        Address address = new Address("haha","helo");
        Address address1 = new Address();
        BeanUtils.copyProperties(address,address1);
        String street = address1.getStreet();
        String country = address1.getCountry();
        System.out.println(street);
        System.out.println(country);
    }
}
