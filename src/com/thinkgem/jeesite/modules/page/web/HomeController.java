/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.page.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

import com.thinkgem.jeesite.modules.page.util.EmojiUtil;
import com.thinkgem.jeesite.modules.zl.entity.ZlUser;
import com.thinkgem.jeesite.modules.zl.service.ZlUserService;

/**
 * 购物车
 * 
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/")
public class HomeController extends BaseController {
	@Autowired
	private ZlUserService zlUserService;

	@RequestMapping(value = "page/home")
	public String home(HttpSession session, Model model) {
		ZlUser zlUser = new ZlUser();
		// 正式环境
		//zlUser.setOppenId(getOppen_id(session));
		// 测试数据
		zlUser.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");

		ZlUser user = zlUserService.get(zlUser);
		user.setRealname(EmojiUtil.emojiRecovery(user.getRealname()));
		model.addAttribute("zlUser", user);
		return "modules/page/home";
	}

}