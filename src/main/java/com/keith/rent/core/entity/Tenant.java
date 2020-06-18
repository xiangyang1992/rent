package com.keith.rent.core.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 租客表(Tenant)实体类
 *
 * @author xiangyang
 * @since 2020-05-27 15:03:49
 */
@Data
public class Tenant implements Serializable {
    private static final long serialVersionUID = 654815155463768688L;
    /**
     * 租客ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tenantId;
    /**
    * 租客姓名
    */
    @NotBlank(message = "租客姓名不能为空")
    private String tenantName;
    /**
    * 房间ID
    */
    @NotNull(message = "房间ID不能为空")
    private Integer roomId;
    /**
    * 公寓id
    */
    private Integer apartmentId;
    /**
    * 入住时间
    */
    @NotNull(message = "入住时间不能为空")
    private Date checkInTime;
    /**
    * 退房时间
    */
    private Date checkOutTime;
    /**
    * 身份证号
    */
    private String identityCard;
    /**
    * 手机号
    */
    private String phone;
    /**
    * 性别
    */
    private Integer sex;
    /**
    * 是否在住(1在住，0退房)
    */
    private Integer status;
    /**
    * 身份证地址
    */
    private String address;

    /**
     * 房间信息(非数据库字段)
     */
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}