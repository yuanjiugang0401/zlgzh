package com.thinkgem.jeesite.modules.page.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zl.entity.ZlAddress;
import com.thinkgem.jeesite.modules.zl.entity.ZlUser;
import com.thinkgem.jeesite.modules.zl.service.ZlAddressService;

/**
 * 购物车
 * 
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/")
public class AddressController extends BaseController {

	@Autowired
	private ZlAddressService zlAddressService;

	/**
	 * 列表页面
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "page/addresslist")
	public String addresslist(HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		ZlAddress address = new ZlAddress();
		address.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");// 测试环境
		// zlAddress.setOppenId(getOppen_id(session));//开发环境
		List<ZlAddress> list = zlAddressService.findList(address);
		model.addAttribute("addressList", list);
		return "modules/page/addressList";
	}
	
	@RequestMapping(value = "page/orderAddressList")
	public String orderAddressList(HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response,ZlAddress zlAddress) {
		ZlAddress address = new ZlAddress();
		address.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");// 测试环境
		// zlAddress.setOppenId(getOppen_id(session));//开发环境
		List<ZlAddress> list = zlAddressService.findList(address);
		model.addAttribute("goodsId", zlAddress.getGoodsId());
		model.addAttribute("addressList", list);
		return "modules/page/orderAddressList";
	}
	/**
	 * 跳转到添加页面
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "page/addressInfo")
	public String addressInfo(HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String id) {
		ZlAddress entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = zlAddressService.get(new ZlAddress(id));
		}
		if (entity == null) {
			entity = new ZlAddress();
		}
		model.addAttribute("address", entity);
		return "modules/page/addressInfo";
	}
	/**
	 *订单用的地址修改
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "page/orderAddressInfo")
	public String orderAddressInfo(HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String id) {
		ZlAddress entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = zlAddressService.get(new ZlAddress(id));
		}
		if (entity == null) {
			entity = new ZlAddress();
		}
		model.addAttribute("address", entity);
		return "modules/page/orderAddressInfo";
	}
	/**
	 * 添加和修改
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @param zlAddress
	 * @return
	 */
	@RequestMapping(value = "page/addressAdd")
	public String addressAdd(HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response,
			ZlAddress zlAddress) {
		zlAddress.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");// 测试环境
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = 0;
		if(zlAddress.getIsDefault().equals("1")){
			// 第一步修改所有的
			zlAddressService.updateAll(zlAddress);
		}//判斷是修改還是刪除 如果是修改
		if(StringUtils.isNotBlank(zlAddress.getId())){
			
			ZlAddress address = zlAddressService.get(zlAddress);
			zlAddress.setId(address.getId());
			zlAddress.setIsNewRecord(false);
		}else{
			zlAddress.setIsNewRecord(true);
		}
		zlAddressService.save(zlAddress);
		rs=1;
		map.put("rs_code", rs);
		return renderString(response, map);
	}
	/**
	 * 删除
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @param zlAddress
	 * @return
	 */
	@RequestMapping(value = "page/addressDel")
	public String addressDel(HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response,
			ZlAddress zlAddress) {
		zlAddressService.delete(zlAddress);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rs_code", 1);
		return renderString(response, map);
	}
}
