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
import com.thinkgem.jeesite.modules.zl.entity.ZlCart;
import com.thinkgem.jeesite.modules.zl.service.ZlCartService;

/**
 * 购物车Controller
 * @author yjg
 * @version 2018-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/zl/zlCart")
public class ZlCartController extends BaseController {

	@Autowired
	private ZlCartService zlCartService;
	
	@ModelAttribute
	public ZlCart get(@RequestParam(required=false) String id) {
		ZlCart entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlCartService.get(id);
		}
		if (entity == null){
			entity = new ZlCart();
		}
		return entity;
	}
	
	@RequiresPermissions("zl:zlCart:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlCart zlCart, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlCart> page = zlCartService.findPage(new Page<ZlCart>(request, response), zlCart); 
		model.addAttribute("page", page);
		return "modules/zl/zlCartList";
	}

	@RequiresPermissions("zl:zlCart:view")
	@RequestMapping(value = "form")
	public String form(ZlCart zlCart, Model model) {
		model.addAttribute("zlCart", zlCart);
		return "modules/zl/zlCartForm";
	}

	@RequiresPermissions("zl:zlCart:edit")
	@RequestMapping(value = "save")
	public String save(ZlCart zlCart, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlCart)){
			return form(zlCart, model);
		}
		zlCartService.save(zlCart);
		addMessage(redirectAttributes, "保存购物车成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlCart/?repage";
	}
	
	@RequiresPermissions("zl:zlCart:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlCart zlCart, RedirectAttributes redirectAttributes) {
		zlCartService.delete(zlCart);
		addMessage(redirectAttributes, "删除购物车成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlCart/?repage";
	}

}