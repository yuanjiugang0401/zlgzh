/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.zl.entity.MyNews;
import com.thinkgem.jeesite.modules.zl.service.MyNewsService;

/**
 * 美柚新闻Controller
 * @author yjg
 * @version 2018-09-18
 */
@Controller
@RequestMapping(value = "${adminPath}/zl/myNews")
public class MyNewsController extends BaseController {

	@Autowired
	private MyNewsService myNewsService;
	
	@ModelAttribute
	public MyNews get(@RequestParam(required=false) String id) {
		MyNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = myNewsService.get(id);
		}
		if (entity == null){
			entity = new MyNews();
		}
		return entity;
	}
	
	@RequiresPermissions("zl:myNews:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyNews myNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MyNews> page = myNewsService.findPage(new Page<MyNews>(request, response), myNews); 
		model.addAttribute("page", page);
		return "modules/zl/myNewsList";
	}

	@RequiresPermissions("zl:myNews:view")
	@RequestMapping(value = "form")
	public String form(MyNews myNews, Model model) {
		model.addAttribute("myNews", myNews);
		return "modules/zl/myNewsForm";
	}

	@RequiresPermissions("zl:myNews:edit")
	@RequestMapping(value = "save")
	public String save(MyNews myNews, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, myNews)){
			return form(myNews, model);
		}
		myNewsService.save(myNews);
		addMessage(redirectAttributes, "保存美柚新闻成功");
		return "redirect:"+Global.getAdminPath()+"/zl/myNews/?repage";
	}
	
	
	@RequestMapping(value = "newsList")
	public String newsList( HttpServletRequest request, HttpServletResponse response,MyNews myNews) {
		List<MyNews> list=myNewsService.findList(myNews);
		return renderString(response, list);
	}
	
	@RequestMapping(value = "detail")
	public String detail( HttpServletRequest request, HttpServletResponse response,String id) {
		MyNews entity = myNewsService.get(id);
		return renderString(response, entity);
	}
	@RequiresPermissions("zl:myNews:edit")
	@RequestMapping(value = "delete")
	public String delete(MyNews myNews, RedirectAttributes redirectAttributes) {
		myNewsService.delete(myNews);
		addMessage(redirectAttributes, "删除美柚新闻成功");
		return "redirect:"+Global.getAdminPath()+"/zl/myNews/?repage";
	}

	
}