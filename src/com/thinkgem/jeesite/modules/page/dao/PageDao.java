/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.page.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zl.entity.ZlWxSetting;

/**
 * 微信设置DAO接口
 * @author yjg
 * @version 2018-09-14
 */
@MyBatisDao
public interface PageDao extends CrudDao<ZlWxSetting> {
	
}