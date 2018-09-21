/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 地址Entity
 * @author yjg
 * @version 2018-09-20
 */
public class ZlAddress extends DataEntity<ZlAddress> {
	
	private static final long serialVersionUID = 1L;
	private String addrUser;		// 收件人
	private String addrTel;		// 收件电话
	private String province;		// 收件省份
	private String city;		// 收件城市
	private String area;		// 收件区域
	private String addrName;		// 收货地址
	private String addrCity;		// 城市
	private String isDefault;		// 是否默认
	private String oppenId;		// 地址归属者
	private String goodsId;     //商品編號
	
	public ZlAddress() {
		super();
	}

	public ZlAddress(String id){
		super(id);
	}

	@Length(min=0, max=255, message="收件人长度必须介于 0 和 255 之间")
	public String getAddrUser() {
		return addrUser;
	}

	public void setAddrUser(String addrUser) {
		this.addrUser = addrUser;
	}
	
	@Length(min=0, max=255, message="收件电话长度必须介于 0 和 255 之间")
	public String getAddrTel() {
		return addrTel;
	}

	public void setAddrTel(String addrTel) {
		this.addrTel = addrTel;
	}
	
	@Length(min=0, max=500, message="收件省份长度必须介于 0 和 500 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=255, message="收件城市长度必须介于 0 和 255 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=255, message="收件区域长度必须介于 0 和 255 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=500, message="收货地址长度必须介于 0 和 500 之间")
	public String getAddrName() {
		return addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	
	@Length(min=0, max=500, message="城市长度必须介于 0 和 500 之间")
	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	
	@Length(min=0, max=1, message="是否默认长度必须介于 0 和 1 之间")
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	@Length(min=0, max=500, message="地址归属者长度必须介于 0 和 500 之间")
	public String getOppenId() {
		return oppenId;
	}

	public void setOppenId(String oppenId) {
		this.oppenId = oppenId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
}