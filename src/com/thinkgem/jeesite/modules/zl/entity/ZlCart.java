/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 购物车Entity
 * @author yjg
 * @version 2018-09-19
 */
public class ZlCart extends DataEntity<ZlCart> {
	
	private static final long serialVersionUID = 1L;
	private String goodsId;		// 商品编号
	private String goodsName;		// 商品名称
	private String goodsPic;		// 商品图片
	private String goodsSpe;		// 商品规格
	private Float goodsPrice;		// 商品价格
	private int goodsNum;		// 商品数量
	private Float goodsTotal;		// 商品总价
	private String oppenId;		// 购买者
	private String status;		// 商品状态
	private Date updateTime;		// 修改时间
	private Integer s;
	
	public ZlCart() {
		super();
	}

	public ZlCart(String id){
		super(id);
	}

	@Length(min=0, max=255, message="商品编号长度必须介于 0 和 255 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	@Length(min=0, max=255, message="商品名称长度必须介于 0 和 255 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getGoodsPic() {
		return goodsPic;
	}

	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}
	
	@Length(min=0, max=255, message="商品规格长度必须介于 0 和 255 之间")
	public String getGoodsSpe() {
		return goodsSpe;
	}

	public void setGoodsSpe(String goodsSpe) {
		this.goodsSpe = goodsSpe;
	}
	
	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
	public Float getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(Float goodsTotal) {
		this.goodsTotal = goodsTotal;
	}
	
	@Length(min=0, max=500, message="购买者长度必须介于 0 和 500 之间")
	public String getOppenId() {
		return oppenId;
	}

	public void setOppenId(String oppenId) {
		this.oppenId = oppenId;
	}
	
	@Length(min=0, max=1, message="商品状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Integer getS() {
		return s;
	}

	public void setS(Integer s) {
		this.s = s;
	}
	
}