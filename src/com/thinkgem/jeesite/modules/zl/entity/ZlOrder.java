/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单Entity
 * @author yjg
 * @version 2018-09-21
 */
public class ZlOrder extends DataEntity<ZlOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单编号
	private String goodsId;		// 商品编号
	private String goodsName;		// 商品名称
	private String goodsPic;		// 商品图片
	private String goodsSpe;		// 商品规格
	private String goodsPrice;		// 商品价格
	private String goodsNum;		// 商品数量
	private String goodsTotalPrice;		// 商品总价
	private String goodsTotalNum;		// 商品总数
	private String addrName;		// 收货地址
	private String receive;		// 配送方式
	private String oppenId;		// 购买人
	private String payType;		// 付款方式
	private String status;		// 订单状态
	private String note;		// 商品备注
	private String expressDm;		// 快递代码
	private String expressName;		// 快递名称
	private String expressHm;		// 快递单号
	private String addrId;  //地址编号
	
	public ZlOrder() {
		super();
	}

	public ZlOrder(String id){
		super(id);
	}

	@Length(min=0, max=500, message="订单编号长度必须介于 0 和 500 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=500, message="商品编号长度必须介于 0 和 500 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
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
	
	@Length(min=0, max=500, message="商品价格长度必须介于 0 和 500 之间")
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	@Length(min=0, max=500, message="商品数量长度必须介于 0 和 500 之间")
	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	public String getGoodsTotalPrice() {
		return goodsTotalPrice;
	}

	public void setGoodsTotalPrice(String goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	
	@Length(min=0, max=11, message="商品总数长度必须介于 0 和 11 之间")
	public String getGoodsTotalNum() {
		return goodsTotalNum;
	}

	public void setGoodsTotalNum(String goodsTotalNum) {
		this.goodsTotalNum = goodsTotalNum;
	}
	
	@Length(min=0, max=500, message="收货地址长度必须介于 0 和 500 之间")
	public String getAddrName() {
		return addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	
	@Length(min=0, max=500, message="配送方式长度必须介于 0 和 500 之间")
	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}
	
	@Length(min=0, max=500, message="购买人长度必须介于 0 和 500 之间")
	public String getOppenId() {
		return oppenId;
	}

	public void setOppenId(String oppenId) {
		this.oppenId = oppenId;
	}
	
	@Length(min=0, max=10, message="付款方式长度必须介于 0 和 10 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=1, message="订单状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Length(min=0, max=500, message="快递代码长度必须介于 0 和 500 之间")
	public String getExpressDm() {
		return expressDm;
	}

	public void setExpressDm(String expressDm) {
		this.expressDm = expressDm;
	}
	
	@Length(min=0, max=500, message="快递名称长度必须介于 0 和 500 之间")
	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	
	@Length(min=0, max=500, message="快递单号长度必须介于 0 和 500 之间")
	public String getExpressHm() {
		return expressHm;
	}

	public void setExpressHm(String expressHm) {
		this.expressHm = expressHm;
	}

	public String getAddrId() {
		return addrId;
	}

	public void setAddrId(String addrId) {
		this.addrId = addrId;
	}
	
}