/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zl.entity.ZlUser;

/**
 * 微信用户DAO接口
 * @author yjg
 * @version 2018-09-14
 */
@MyBatisDao
public interface ZlUserDao extends CrudDao<ZlUser> {
	
}