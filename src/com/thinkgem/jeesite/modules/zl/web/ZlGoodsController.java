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
import com.thinkgem.jeesite.modules.zl.entity.ZlGoods;
import com.thinkgem.jeesite.modules.zl.service.ZlGoodsService;

/**
 * 中粮商品Controller
 * @author yjg
 * @version 2018-09-18
 */
@Controller
@RequestMapping(value = "${adminPath}/zl/zlGoods")
public class ZlGoodsController extends BaseController {

	@Autowired
	private ZlGoodsService zlGoodsService;
	
	@ModelAttribute
	public ZlGoods get(@RequestParam(required=false) String id) {
		ZlGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlGoodsService.get(id);
		}
		if (entity == null){
			entity = new ZlGoods();
		}
		return entity;
	}
	
	@RequiresPermissions("zl:zlGoods:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlGoods zlGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlGoods> page = zlGoodsService.findPage(new Page<ZlGoods>(request, response), zlGoods); 
		model.addAttribute("page", page);
		return "modules/zl/zlGoodsList";
	}

	@RequiresPermissions("zl:zlGoods:view")
	@RequestMapping(value = "form")
	public String form(ZlGoods zlGoods, Model model) {
		model.addAttribute("zlGoods", zlGoods);
		return "modules/zl/zlGoodsForm";
	}

	@RequiresPermissions("zl:zlGoods:edit")
	@RequestMapping(value = "save")
	public String save(ZlGoods zlGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlGoods)){
			return form(zlGoods, model);
		}
		zlGoodsService.save(zlGoods);
		addMessage(redirectAttributes, "保存中粮商品成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlGoods/?repage";
	}
	
	@RequiresPermissions("zl:zlGoods:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlGoods zlGoods, RedirectAttributes redirectAttributes) {
		zlGoodsService.delete(zlGoods);
		addMessage(redirectAttributes, "删除中粮商品成功");
		return "redirect:"+Global.getAdminPath()+"/zl/zlGoods/?repage";
	}

}