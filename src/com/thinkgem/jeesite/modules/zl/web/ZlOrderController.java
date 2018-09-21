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
import com.thinkgem.jeesite.modules.zl.entity.ZlOrder;
import com.thinkgem.jeesite.modules.zl.service.ZlOrderService;

/**
 * 订单Controller
 * @author yjg
 * @version 2018-09-21
 */
@Controller
@RequestMapping(value = "${adminPath}/zl/zlOrder")
public class ZlOrderController extends BaseController {

	@Autowired
	private ZlOrderService zlOrderService;
	
	@ModelAttribute
	public ZlOrder get(@RequestParam(required=false) String id) {
		ZlOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlOrderService.get(id);
		}
		if (entity == null){
			entity = new ZlOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("zl:zlOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlOrder zlOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlOrder> page = zlOrderService.findPage(new Page<ZlOrder>(request, response), zlOrder); 
		model.addAttribute("page", page);
		return "modules/zl/zlOrderList";
	}

	@RequiresPermissions("zl:zlOrder:view")
	@RequestMapping(value = "form")
	public String form(ZlOrder zlOrder, Model model) {
		model.addAttribute("zlOrder", zlOrder);
		return "modules/zl/zlOrderForm";
	}

	@RequiresPermissions("zl:zlOrder:edit")
	@RequestMapping(value = "save")
	public String save(ZlOrder zlOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlOrder)){
			return form(zlOrder, model);
		}
		zlOrderService.save(zlOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlOrder/?repage";
	}
	
	@RequiresPermissions("zl:zlOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlOrder zlOrder, RedirectAttributes redirectAttributes) {
		zlOrderService.delete(zlOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlOrder/?repage";
	}

}