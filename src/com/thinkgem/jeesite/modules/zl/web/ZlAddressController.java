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
import com.thinkgem.jeesite.modules.zl.entity.ZlAddress;
import com.thinkgem.jeesite.modules.zl.service.ZlAddressService;

/**
 * 地址Controller
 * @author yjg
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/zl/zlAddress")
public class ZlAddressController extends BaseController {

	@Autowired
	private ZlAddressService zlAddressService;
	
	@ModelAttribute
	public ZlAddress get(@RequestParam(required=false) String id) {
		ZlAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlAddressService.get(id);
		}
		if (entity == null){
			entity = new ZlAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("zl:zlAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlAddress zlAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlAddress> page = zlAddressService.findPage(new Page<ZlAddress>(request, response), zlAddress); 
		model.addAttribute("page", page);
		return "modules/zl/zlAddressList";
	}

	@RequiresPermissions("zl:zlAddress:view")
	@RequestMapping(value = "form")
	public String form(ZlAddress zlAddress, Model model) {
		model.addAttribute("zlAddress", zlAddress);
		return "modules/zl/zlAddressForm";
	}

	@RequiresPermissions("zl:zlAddress:edit")
	@RequestMapping(value = "save")
	public String save(ZlAddress zlAddress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlAddress)){
			return form(zlAddress, model);
		}
		zlAddressService.save(zlAddress);
		addMessage(redirectAttributes, "保存地址成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlAddress/?repage";
	}
	
	@RequiresPermissions("zl:zlAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlAddress zlAddress, RedirectAttributes redirectAttributes) {
		zlAddressService.delete(zlAddress);
		addMessage(redirectAttributes, "删除地址成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlAddress/?repage";
	}

}