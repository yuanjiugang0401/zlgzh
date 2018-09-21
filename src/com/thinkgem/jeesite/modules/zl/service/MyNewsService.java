/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zl.entity.MyNews;
import com.thinkgem.jeesite.modules.zl.dao.MyNewsDao;

/**
 * 美柚新闻Service
 * @author yjg
 * @version 2018-09-18
 */
@Service
@Transactional(readOnly = true)
public class MyNewsService extends CrudService<MyNewsDao, MyNews> {

	public MyNews get(String id) {
		return super.get(id);
	}
	
	public List<MyNews> findList(MyNews myNews) {
		return super.findList(myNews);
	}
	
	public Page<MyNews> findPage(Page<MyNews> page, MyNews myNews) {
		return super.findPage(page, myNews);
	}
	
	@Transactional(readOnly = false)
	public void save(MyNews myNews) {
		super.save(myNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(MyNews myNews) {
		super.delete(myNews);
	}
	
}