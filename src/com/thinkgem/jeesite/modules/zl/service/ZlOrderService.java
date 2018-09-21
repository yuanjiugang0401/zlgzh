/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zl.entity.ZlOrder;
import com.thinkgem.jeesite.modules.zl.dao.ZlOrderDao;

/**
 * 订单Service
 * @author yjg
 * @version 2018-09-21
 */
@Service
@Transactional(readOnly = true)
public class ZlOrderService extends CrudService<ZlOrderDao, ZlOrder> {

	public ZlOrder get(String id) {
		return super.get(id);
	}
	
	public List<ZlOrder> findList(ZlOrder zlOrder) {
		return super.findList(zlOrder);
	}
	
	public Page<ZlOrder> findPage(Page<ZlOrder> page, ZlOrder zlOrder) {
		return super.findPage(page, zlOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlOrder zlOrder) {
		super.save(zlOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlOrder zlOrder) {
		super.delete(zlOrder);
	}
	
}