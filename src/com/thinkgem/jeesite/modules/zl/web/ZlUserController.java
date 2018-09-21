/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.zl.entity.ZlUser;
import com.thinkgem.jeesite.modules.zl.service.ZlUserService;

/**
 * 微信用户Controller
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/zl/zlUser")
public class ZlUserController extends BaseController {

	@Autowired
	private ZlUserService zlUserService;
	
	@ModelAttribute
	public ZlUser get(@RequestParam(required=false) String id) {
		ZlUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlUserService.get(id);
		}
		if (entity == null){
			entity = new ZlUser();
		}
		return entity;
	}
	
	@RequiresPermissions("zl:zlUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlUser zlUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlUser> page = zlUserService.findPage(new Page<ZlUser>(request, response), zlUser); 
		model.addAttribute("page", page);
		return "modules/zl/zlUserList";
	}

	@RequiresPermissions("zl:zlUser:view")
	@RequestMapping(value = "form")
	public String form(ZlUser zlUser, Model model) {
		model.addAttribute("zlUser", zlUser);
		return "modules/zl/zlUserForm";
	}

	@RequiresPermissions("zl:zlUser:edit")
	@RequestMapping(value = "save")
	public String save(ZlUser zlUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlUser)){
			return form(zlUser, model);
		}
		zlUserService.save(zlUser);
		addMessage(redirectAttributes, "保存微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlUser/?repage";
	}
	
	@RequiresPermissions("zl:zlUser:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlUser zlUser, RedirectAttributes redirectAttributes) {
		zlUserService.delete(zlUser);
		addMessage(redirectAttributes, "删除微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlUser/?repage";
	}

}