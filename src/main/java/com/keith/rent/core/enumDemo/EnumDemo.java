package com.keith.rent.core.enumDemo;

/**
 * description:
 * author:xiangyang
 */
public enum EnumDemo {

    MEMBER_BASE_CONFIG("tenant"),
    MEMBER_INFO("sys_room_detail"),
    MEMBER_CONFIG("sys_role");

    String name;

    EnumDemo(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
