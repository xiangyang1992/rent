package com.keith.rent.core.entity;

import java.io.Serializable;

/**
 * (RoomDetail)实体类
 *
 * @author makejava
 * @since 2020-05-03 14:38:25
 */
public class RoomDetail implements Serializable {
    private static final long serialVersionUID = 256618989123101316L;
    
    private Integer id;
    
    private String roomcost;
    
    private String roomcashpledge;
    
    private String roomcardcash;
    
    private String roomothercash;
    
    private String mangerfee;
    
    private String interfee;
    
    private String waterfee;
    
    private String otherfee;
    
    private String total;
    
    private Integer roomid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomcost() {
        return roomcost;
    }

    public void setRoomcost(String roomcost) {
        this.roomcost = roomcost;
    }

    public String getRoomcashpledge() {
        return roomcashpledge;
    }

    public void setRoomcashpledge(String roomcashpledge) {
        this.roomcashpledge = roomcashpledge;
    }

    public String getRoomcardcash() {
        return roomcardcash;
    }

    public void setRoomcardcash(String roomcardcash) {
        this.roomcardcash = roomcardcash;
    }

    public String getRoomothercash() {
        return roomothercash;
    }

    public void setRoomothercash(String roomothercash) {
        this.roomothercash = roomothercash;
    }

    public String getMangerfee() {
        return mangerfee;
    }

    public void setMangerfee(String mangerfee) {
        this.mangerfee = mangerfee;
    }

    public String getInterfee() {
        return interfee;
    }

    public void setInterfee(String interfee) {
        this.interfee = interfee;
    }

    public String getWaterfee() {
        return waterfee;
    }

    public void setWaterfee(String waterfee) {
        this.waterfee = waterfee;
    }

    public String getOtherfee() {
        return otherfee;
    }

    public void setOtherfee(String otherfee) {
        this.otherfee = otherfee;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

}