/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zl.entity.MyNews;

/**
 * 美柚新闻DAO接口
 * @author yjg
 * @version 2018-09-18
 */
@MyBatisDao
public interface MyNewsDao extends CrudDao<MyNews> {
	
}