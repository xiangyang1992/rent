package com.keith.rent.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * (Apartment)实体类
 *
 * @author makejava
 * @since 2020-05-03 14:38:48
 */
@Data
public class Apartment implements Serializable {

    private static final long serialVersionUID = -97234559229866497L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer apartmentId;
    @NotNull(message = "公寓名称不能为空")
    private String apartmentName;
    private Integer apartmentMangerId;
    @NotNull(message = "公寓管理员名称不能为空")
    private String apartmentMangerName;
    @NotNull(message = "创建时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zn", timezone = "GMT+8")
    private Date createTime;
    @NotNull(message = "创建人不能为空")
    private String createPerson;
}