/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.page.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zl.entity.ZlCart;
import com.thinkgem.jeesite.modules.zl.service.ZlCartService;

/**
 * 购物车
 * 
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/")
public class ShopCarController extends BaseController {

	@Autowired
	private ZlCartService zlCartService;

	/**
	 * 购物车首页
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "page/shopcar")
	public String shopcar(HttpSession session, Model model) {
		ZlCart zlCart = new ZlCart();
		// 开发环境
		//zlCart.setOppenId(getOppen_id(session));
		//测试环境
		zlCart.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
		List<ZlCart> list = zlCartService.findList(zlCart);
		Float tprice = zlCartService.goodsTotalPrice(zlCart);
		Integer tnum = zlCartService.goodsTotalNum(zlCart);
		model.addAttribute("cartList", list);
		//model.addAttribute("tprice", tprice);
		model.addAttribute("tnum", tnum);
		return "modules/page/shopcar";
	}

	/**
	 * 添加到购物车
	 * 
	 * @param session
	 * @param zlCart
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "page/cartInsert")
	public String cartInsert(HttpSession session, ZlCart zlCart,
			HttpServletRequest request, HttpServletResponse response) {
		// 存放返回值
		Map<String, Object> map = new HashMap<String, Object>();
		// zlCart.setGoodsId(zlCart.getGoodsId());
		//zlCart.setOppenId(getOppen_id(session));
		zlCart.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk"); //测试环境
		int cart_num = Integer.parseInt(session.getAttribute("cart_num")
				.toString()) + 1;
		session.setAttribute("cart_num", cart_num);
		// 查询购物车中，登录人，选择商品的数量
		ZlCart cart = zlCartService.get(zlCart);
		int rs = 0;
		int goodsNum = 0;
		// 修改
		if (cart != null) {
			// 表示购物车中，有买个此商品，就修改
			goodsNum = cart.getGoodsNum() + zlCart.getGoodsNum();
			cart.setGoodsTotal(zlCart.getGoodsPrice() * goodsNum);
			cart.setGoodsNum(goodsNum);
			cart.setIsNewRecord(false);
			zlCartService.save(cart);
			rs = 1;
		} else {
			// 表示购物车中，没有这个商品，就新添加
			goodsNum = zlCart.getGoodsNum();
			zlCart.setGoodsTotal(zlCart.getGoodsPrice() * goodsNum);
			zlCart.setIsNewRecord(true);
			zlCartService.save(zlCart);
			rs = 1;
		}
		map.put("rs_code", rs);
		map.put("cart_num", cart_num);
		return renderString(response, map);
	}

	@RequestMapping(value = "page/cartUpdate")
	public String cartUpdate(HttpSession session, ZlCart zlCart,
			HttpServletRequest request, HttpServletResponse response) {
		// 存放返回值
		Map<String, Object> map = new HashMap<String, Object>();
		//zlCart.setOppenId(getOppen_id(session));
		zlCart.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk"); //测试环境
		int cart_num = Integer.parseInt(session.getAttribute("cart_num")
				.toString());
		if (zlCart.getS() == 1) {
			cart_num = cart_num + 1;
		} else {
			cart_num = cart_num - 1;
		}
		session.setAttribute("cart_num", cart_num);
		// 查询购物车中，登录人，选择商品的数量
		ZlCart cart = zlCartService.get(zlCart);
		int rs = 0;
		// 修改
		if (cart != null) {
			cart.setGoodsTotal(zlCart.getGoodsPrice() * zlCart.getGoodsNum());
			cart.setGoodsNum(zlCart.getGoodsNum());
			cart.setIsNewRecord(false);
			zlCartService.save(cart);
			rs = 1;
		}
		map.put("rs_code", rs);
		map.put("cart_num", cart_num);
		return renderString(response, map);
	}
	@RequestMapping(value = "page/cartDel")
	public String cartDel(ZlCart zlCart,HttpSession session,HttpServletResponse response,HttpServletRequest request){
		// 存放返回值
		Map<String, Object> map = new HashMap<String, Object>();
		//查询该商品
		ZlCart cart=zlCartService.get(zlCart);
		int cart_num=0;
		int rs=0;
		if(cart!=null){
			cart_num =Integer.parseInt(session.getAttribute("cart_num").toString())-cart.getGoodsNum();
			rs=1;
		}else{
			rs=-1;
		}
		session.setAttribute("cart_num", cart_num);
		zlCartService.delete(zlCart);
		map.put("rs_code", rs);
		map.put("cart_num", cart_num);
		return renderString(response, map);
	}
}