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

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.page.util.EmojiUtil;
import com.thinkgem.jeesite.modules.weixin.util.WxUtil;
import com.thinkgem.jeesite.modules.zl.entity.ZlCart;
import com.thinkgem.jeesite.modules.zl.entity.ZlGoods;
import com.thinkgem.jeesite.modules.zl.entity.ZlUser;
import com.thinkgem.jeesite.modules.zl.entity.ZlWxSetting;
import com.thinkgem.jeesite.modules.zl.service.ZlCartService;
import com.thinkgem.jeesite.modules.zl.service.ZlGoodsService;
import com.thinkgem.jeesite.modules.zl.service.ZlUserService;
import com.thinkgem.jeesite.modules.zl.service.ZlWxSettingService;

/**
 * 微信设置Controller
 * 
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/")
public class IndexController extends BaseController {

	@Autowired
	private ZlUserService zlUserService;
	@Autowired
	private ZlGoodsService zlGoodsService;
	@Autowired
	private ZlCartService zlCartService;
	private Logger log = Logger.getLogger(getClass());

	@RequestMapping(value = "page/index")
	public String index(HttpSession session, Model model,HttpServletRequest request, HttpServletResponse response) {
		ZlGoods zlGoods=new ZlGoods(); 
		List<ZlGoods> goodsList=zlGoodsService.findList(zlGoods);
		ZlCart zlCart=new ZlCart();
		zlCart.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
		//zlCart.setOppenId(getOppen_id(session));
		long cart_num= zlCartService.goodsTotalNum(zlCart);
		//模拟购物车中数量
		session.setAttribute("cart_num", cart_num);
		model.addAttribute("goodsList", goodsList);
		return "modules/page/index";
	}

	@RequestMapping(value = "page/userInsert")
	public String userInsert(HttpServletRequest request,HttpServletResponse response, HttpSession session,
			Model model, String url) {
		String oppen_id = "";
		String username = "";
		int isUrl = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		if (session.getAttribute("oppen_id") == null) {
			System.out.println("系统开始，检查oppen_id="
					+ session.getAttribute("oppen_id"));
			map = WxUtil.oppenIdInfo(request, session);
			oppen_id = (String) map.get("oppen_id");
			if (StringUtils.isNotEmpty(oppen_id)) {
				ZlUser zlUser = new ZlUser();
				zlUser.setOppenId(oppen_id);
				zlUser.setUsername(username);
				zlUser.setRealname(EmojiUtil.emojiConvert(map.get("realname").toString()));
				zlUser.setHeadImg(map.get("head_img").toString());
				zlUser.setStatus("1");
				setOppen_id(oppen_id, session);
				// session.setAttribute("realname",map.get("realname"));
				// session.setAttribute("head_img",map.get("head_img"));
				List<ZlUser> userList = zlUserService.findList(zlUser);
				if (userList.size() != 0) {
					System.out.println("用户存在，则update");
					log.info("用户存在，则update");
					zlUser.setId(userList.get(0).getId());
					zlUserService.save(zlUser);
					isUrl = 1;
				} else {
					System.out.println("用户不存在，则insert");
					log.info("用户不存在，则insert");
					zlUser.setIsNewRecord(true);
					zlUserService.save(zlUser);
					isUrl = 1;
				}
				ZlCart zlCart=new ZlCart();
				zlCart.setOppenId(oppen_id);
				long cart_num= zlCartService.goodsTotalNum(zlCart);
				session.setAttribute("cart_num", cart_num);
			} else {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println("oppen_id==null");
				isUrl = -1;
			}
		} else {
			isUrl = 1;
		}
		url = "redirect:" + url;
		String reUrl = (isUrl == 1) ? url : "page/error";
		return reUrl;
	}
}