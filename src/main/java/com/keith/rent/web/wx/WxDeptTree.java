package com.keith.rent.web.wx;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WxDeptTree {

	private String id;
	private String name;
	private String parentid;
	private long order;
	// 当对接第三方用户中心时，此id才是真正的政务微信部门id
	private String actualWxId;
	//子部门，此初始化不能去掉，也不能放在其他地方初始化，必须在实例化的时候初始化
	private List<WxDeptTree> childrenDept = new ArrayList<WxDeptTree>();

	// 对接第三方拓展字段
	private String extCode;

	public void setOrder(long order) {
		this.order = order;
	}

	public String getActualWxId() {
		return actualWxId;
	}

	public void setActualWxId(String actualWxId) {
		this.actualWxId = actualWxId;
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public  WxDeptTree() {
		super();
	}

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	/**
	 * @return childrenDept
	 */
	public List<WxDeptTree> getChildrenDept() {
		return childrenDept;
	}

	/**
	 * @param childrenDept 要设置的 childrenDept
	 */
	public void setChildrenDept(List<WxDeptTree> childrenDept) {
		this.childrenDept = childrenDept;
	}

	/**
	 * @param childDept 要设置的 childrenDept
	 */
	public void addChildrenDept(WxDeptTree childDept) {
		this.childrenDept.add(childDept);
	}

    public long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    /**
	 * @return order
	 *//*
	public Integer getOrder() {
		return order;
	}

	*//**
	 * @param order 要设置的 order
	 *//*
	public void setOrder(Integer order) {
		this.order = order;
	}*/

}
