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
import com.thinkgem.jeesite.modules.zl.entity.ZlWxSetting;
import com.thinkgem.jeesite.modules.zl.service.ZlWxSettingService;

/**
 * 微信设置Controller
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/zl/zlWxSetting")
public class ZlWxSettingController extends BaseController {

	@Autowired
	private ZlWxSettingService zlWxSettingService;
	
	@ModelAttribute
	public ZlWxSetting get(@RequestParam(required=false) String id) {
		ZlWxSetting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlWxSettingService.get(id);
		}
		if (entity == null){
			entity = new ZlWxSetting();
		}
		return entity;
	}
	
	@RequiresPermissions("zl:zlWxSetting:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlWxSetting zlWxSetting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlWxSetting> page = zlWxSettingService.findPage(new Page<ZlWxSetting>(request, response), zlWxSetting); 
		model.addAttribute("page", page);
		return "modules/zl/zlWxSettingList";
	}

	@RequiresPermissions("zl:zlWxSetting:view")
	@RequestMapping(value = "form")
	public String form(ZlWxSetting zlWxSetting, Model model) {
		model.addAttribute("zlWxSetting", zlWxSetting);
		return "modules/zl/zlWxSettingForm";
	}

	@RequiresPermissions("zl:zlWxSetting:edit")
	@RequestMapping(value = "save")
	public String save(ZlWxSetting zlWxSetting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlWxSetting)){
			return form(zlWxSetting, model);
		}
		zlWxSettingService.save(zlWxSetting);
		addMessage(redirectAttributes, "保存微信设置成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlWxSetting/?repage";
	}
	
	@RequiresPermissions("zl:zlWxSetting:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlWxSetting zlWxSetting, RedirectAttributes redirectAttributes) {
		zlWxSettingService.delete(zlWxSetting);
		addMessage(redirectAttributes, "删除微信设置成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlWxSetting/?repage";
	}

}