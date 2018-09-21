/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zl.entity.ZlCart;
import com.thinkgem.jeesite.modules.zl.dao.ZlCartDao;

/**
 * 购物车Service
 * @author yjg
 * @version 2018-09-19
 */
@Service
@Transactional(readOnly = true)
public class ZlCartService extends CrudService<ZlCartDao, ZlCart> {

	public ZlCart get(String id) {
		return super.get(id);
	}
	
	public List<ZlCart> findList(ZlCart zlCart) {
		return super.findList(zlCart);
	}
	
	public Page<ZlCart> findPage(Page<ZlCart> page, ZlCart zlCart) {
		return super.findPage(page, zlCart);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlCart zlCart) {
		super.save(zlCart);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlCart zlCart) {
		super.delete(zlCart);
	}
	
	public Float goodsTotalPrice(ZlCart zlCart){
		return dao.goodsTotalPrice(zlCart);
	}
	
	public Integer goodsTotalNum(ZlCart zlCart){
		return dao.goodsTotalNum(zlCart);
	}
}