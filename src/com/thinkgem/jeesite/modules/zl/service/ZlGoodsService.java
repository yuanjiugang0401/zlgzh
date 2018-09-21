/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zl.entity.ZlGoods;
import com.thinkgem.jeesite.modules.zl.dao.ZlGoodsDao;

/**
 * 中粮商品Service
 * @author yjg
 * @version 2018-09-18
 */
@Service
@Transactional(readOnly = true)
public class ZlGoodsService extends CrudService<ZlGoodsDao, ZlGoods> {

	public ZlGoods get(String id) {
		return super.get(id);
	}
	
	public List<ZlGoods> findList(ZlGoods zlGoods) {
		return super.findList(zlGoods);
	}
	
	public Page<ZlGoods> findPage(Page<ZlGoods> page, ZlGoods zlGoods) {
		return super.findPage(page, zlGoods);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlGoods zlGoods) {
		super.save(zlGoods);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlGoods zlGoods) {
		super.delete(zlGoods);
	}
	
}