/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 中粮商品Entity
 * @author yjg
 * @version 2018-09-18
 */
public class ZlGoods extends DataEntity<ZlGoods> {
	
	private static final long serialVersionUID = 1L;
	private String goodsName;		// 商品名称
	private String goodsPic;		// 商品图片
	private String goodsSpe;		// 商品规格
	private String goodsPrice;		// 商品价格
	private String goodsDetail;		// 商品详情
	private String goodsSales;		// 商品销量
	private String goodsType;		// 商品类别
	private String goodsCount;		// 商品库存
	private String status;		// 商品状态
	private Date updateDate;		// 修改时间
	
	public ZlGoods() {
		super();
	}

	public ZlGoods(String id){
		super(id);
	}

	@Length(min=0, max=500, message="商品名称长度必须介于 0 和 500 之间")
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
	
	@Length(min=0, max=500, message="商品规格长度必须介于 0 和 500 之间")
	public String getGoodsSpe() {
		return goodsSpe;
	}

	public void setGoodsSpe(String goodsSpe) {
		this.goodsSpe = goodsSpe;
	}
	
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public String getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
	
	@Length(min=0, max=255, message="商品销量长度必须介于 0 和 255 之间")
	public String getGoodsSales() {
		return goodsSales;
	}

	public void setGoodsSales(String goodsSales) {
		this.goodsSales = goodsSales;
	}
	
	@Length(min=0, max=1, message="商品类别长度必须介于 0 和 1 之间")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	@Length(min=0, max=11, message="商品库存长度必须介于 0 和 11 之间")
	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	@Length(min=0, max=1, message="商品状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
}