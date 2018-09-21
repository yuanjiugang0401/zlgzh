/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zl.entity.ZlWxSetting;
import com.thinkgem.jeesite.modules.zl.dao.ZlWxSettingDao;

/**
 * 微信设置Service
 * @author yjg
 * @version 2018-09-14
 */
@Service
@Transactional(readOnly = true)
public class ZlWxSettingService extends CrudService<ZlWxSettingDao, ZlWxSetting> {

	public ZlWxSetting get(String id) {
		return super.get(id);
	}
	
	public List<ZlWxSetting> findList(ZlWxSetting zlWxSetting) {
		return super.findList(zlWxSetting);
	}
	
	public Page<ZlWxSetting> findPage(Page<ZlWxSetting> page, ZlWxSetting zlWxSetting) {
		return super.findPage(page, zlWxSetting);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlWxSetting zlWxSetting) {
		super.save(zlWxSetting);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlWxSetting zlWxSetting) {
		super.delete(zlWxSetting);
	}
	
}