/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信用户Entity
 * @author yjg
 * @version 2018-09-14
 */
public class ZlUser extends DataEntity<ZlUser> {
	
	private static final long serialVersionUID = 1L;
	private String oppenId;		// 微信唯一标记
	private String username;		// 姓名
	private String realname;		// 真实姓名
	private String headImg;		// 头像
	private String password;		// 密码
	private String status;		// 状态
	private Date memberTime;		// 成为会员的时间
	private String areaId;		// 区域编号
	private String areaName;		// 区域名称
	private Float balance;		// 账户余额
	private Date updateTime;		// 修改时间
	
	public ZlUser() {
		super();
	}

	public ZlUser(String id){
		super(id);
	}

	@Length(min=0, max=255, message="微信唯一标记长度必须介于 0 和 255 之间")
	public String getOppenId() {
		return oppenId;
	}

	public void setOppenId(String oppenId) {
		this.oppenId = oppenId;
	}
	
	@Length(min=0, max=255, message="姓名长度必须介于 0 和 255 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=255, message="真实姓名长度必须介于 0 和 255 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=255, message="头像长度必须介于 0 和 255 之间")
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	@Length(min=0, max=255, message="密码长度必须介于 0 和 255 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMemberTime() {
		return memberTime;
	}

	public void setMemberTime(Date memberTime) {
		this.memberTime = memberTime;
	}
	
	@Length(min=0, max=64, message="区域编号长度必须介于 0 和 64 之间")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	@Length(min=0, max=255, message="区域名称长度必须介于 0 和 255 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}