/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.page.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

import com.thinkgem.jeesite.modules.page.util.EmojiUtil;
import com.thinkgem.jeesite.modules.zl.entity.ZlAddress;
import com.thinkgem.jeesite.modules.zl.entity.ZlCart;
import com.thinkgem.jeesite.modules.zl.entity.ZlOrder;
import com.thinkgem.jeesite.modules.zl.entity.ZlUser;
import com.thinkgem.jeesite.modules.zl.service.ZlAddressService;
import com.thinkgem.jeesite.modules.zl.service.ZlCartService;
import com.thinkgem.jeesite.modules.zl.service.ZlOrderService;
import com.thinkgem.jeesite.modules.zl.service.ZlUserService;

/**
 * 订单
 * 
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/")
public class OrderController extends BaseController {
	@Autowired
	private ZlOrderService zlOrderService;
	@Autowired
	private ZlAddressService zlAddressService;
	@Autowired
	private ZlCartService zlCartService;
	
	

	@RequestMapping(value = "page/orderList")
	public String orderList(HttpSession session, Model model) {
		ZlOrder zlOrder = new ZlOrder();
		// 正式环境
		//zlOrder.setOppenId(getOppen_id(session));
		// 测试数据
		zlOrder.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
		List<ZlOrder> list=zlOrderService.findList(zlOrder);
		model.addAttribute("orderList", list);
		return "modules/page/orderList";
	}
	@RequestMapping(value = "page/cartOrder")
	public String cartOrder(HttpSession session, ZlOrder zlOrder,
			HttpServletRequest request, HttpServletResponse response,Model model){
		
		ZlAddress zlAddress=new ZlAddress();
		ZlAddress address=null;
		if(zlOrder.getAddrId()==null||zlOrder.getAddrId().equals("")){
			//查找默认地址
			zlAddress.setIsDefault("1");
		}else{
			zlAddress.setId(zlOrder.getAddrId());
		}
		zlAddress.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
		address=zlAddressService.selectByIsDefault(zlAddress);
	
		String goodsId=zlOrder.getGoodsId();
		ZlCart zlCart=new ZlCart();
		zlCart.setGoodsId(goodsId);
		zlCart.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
		List<ZlCart> list=zlCartService.findList(zlCart);
		Float totalPrice=zlCartService.goodsTotalPrice(zlCart);
		model.addAttribute("goodsId", goodsId);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("zlAddress", address);
		model.addAttribute("cartList", list);
		return "modules/page/payment";
	} 
}