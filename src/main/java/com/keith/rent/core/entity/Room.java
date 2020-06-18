package com.keith.rent.core.entity;

import java.io.Serializable;

/**
 * (Room)实体类
 *
 * @author makejava
 * @since 2020-05-03 14:38:37
 */
public class Room implements Serializable {
    private static final long serialVersionUID = -52680988011809557L;
    
    private Integer roomid;
    
    private Integer roomnum;
    
    private String roomname;
    
    private Integer roomuserid;


    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Integer getRoomuserid() {
        return roomuserid;
    }

    public void setRoomuserid(Integer roomuserid) {
        this.roomuserid = roomuserid;
    }

}