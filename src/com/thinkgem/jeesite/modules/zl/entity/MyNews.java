/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 美柚新闻Entity
 * @author yjg
 * @version 2018-09-18
 */
public class MyNews extends DataEntity<MyNews> {
	
	private static final long serialVersionUID = 1L;
	private String summary;		// 概要
	private String imageUrl;		// 图片
	private String imageMax;   //大图
	private String content;		// 内容
	private Date updateTime;		// 修改时间
	private String title;
	
	public MyNews() {
		super();
	}

	public MyNews(String id){
		super(id);
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Length(min=0, max=500, message="图片长度必须介于 0 和 500 之间")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageMax() {
		return imageMax;
	}

	public void setImageMax(String imageMax) {
		this.imageMax = imageMax;
	}
	
}