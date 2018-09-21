/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zl.entity.ZlAddress;
import com.thinkgem.jeesite.modules.zl.dao.ZlAddressDao;

/**
 * 地址Service
 * @author yjg
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class ZlAddressService extends CrudService<ZlAddressDao, ZlAddress> {

	public ZlAddress get(String id) {
		return super.get(id);
	}
	
	public List<ZlAddress> findList(ZlAddress zlAddress) {
		return super.findList(zlAddress);
	}
	
	public Page<ZlAddress> findPage(Page<ZlAddress> page, ZlAddress zlAddress) {
		return super.findPage(page, zlAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlAddress zlAddress) {
		super.save(zlAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlAddress zlAddress) {
		super.delete(zlAddress);
	}

	@Transactional(readOnly = false)
	public void updateAll(ZlAddress zlAddress){
		dao.updateAll(zlAddress);
	}
	
	public ZlAddress selectByIsDefault(ZlAddress zlAddress){
		return dao.selectByIsDefault(zlAddress);
	}
}