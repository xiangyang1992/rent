package com.keith.rent.core.entity;

import java.io.Serializable;

/**
 * (WaterEleDetail)实体类
 *
 * @author makejava
 * @since 2020-05-03 14:38:58
 */
public class WaterEleDetail implements Serializable {
    private static final long serialVersionUID = -93715776501766672L;
    
    private Integer id;
    
    private Integer roomid;
    
    private String waterCurrentData;
    
    private String waterLastData;
    
    private String eleCurrentData;
    
    private String eleLastData;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getWaterCurrentData() {
        return waterCurrentData;
    }

    public void setWaterCurrentData(String waterCurrentData) {
        this.waterCurrentData = waterCurrentData;
    }

    public String getWaterLastData() {
        return waterLastData;
    }

    public void setWaterLastData(String waterLastData) {
        this.waterLastData = waterLastData;
    }

    public String getEleCurrentData() {
        return eleCurrentData;
    }

    public void setEleCurrentData(String eleCurrentData) {
        this.eleCurrentData = eleCurrentData;
    }

    public String getEleLastData() {
        return eleLastData;
    }

    public void setEleLastData(String eleLastData) {
        this.eleLastData = eleLastData;
    }

}