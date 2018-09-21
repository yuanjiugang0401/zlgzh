/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.page.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信设置Entity
 * @author yjg
 * @version 2018-09-14
 */
public class PageSetting extends DataEntity<PageSetting> {
	
	private static final long serialVersionUID = 1L;
	private String appid;		// 微信Appid
	private String appsecret;		// 微信AppSecret
	private String partner;		// 微信Partner
	private String partnerkey;		// 微信Partnerkey
	private String link;		// 域名
	
	public PageSetting() {
		super();
	}

	public PageSetting(String id){
		super(id);
	}

	@Length(min=0, max=255, message="微信Appid长度必须介于 0 和 255 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=0, max=255, message="微信AppSecret长度必须介于 0 和 255 之间")
	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	
	@Length(min=0, max=255, message="微信Partner长度必须介于 0 和 255 之间")
	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	@Length(min=0, max=255, message="微信Partnerkey长度必须介于 0 和 255 之间")
	public String getPartnerkey() {
		return partnerkey;
	}

	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	
	@Length(min=0, max=255, message="域名长度必须介于 0 和 255 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}