/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zl.entity.ZlUser;
import com.thinkgem.jeesite.modules.zl.dao.ZlUserDao;

/**
 * 微信用户Service
 * @author yjg
 * @version 2018-09-14
 */
@Service
@Transactional(readOnly = true)
public class ZlUserService extends CrudService<ZlUserDao, ZlUser> {

	public ZlUser get(String id) {
		return super.get(id);
	}
	
	public List<ZlUser> findList(ZlUser zlUser) {
		return super.findList(zlUser);
	}
	
	public Page<ZlUser> findPage(Page<ZlUser> page, ZlUser zlUser) {
		return super.findPage(page, zlUser);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlUser zlUser) {
		super.save(zlUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlUser zlUser) {
		super.delete(zlUser);
	}
	
}