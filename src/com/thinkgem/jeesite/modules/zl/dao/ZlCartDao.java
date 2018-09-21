/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.zl.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zl.entity.ZlCart;



/**
 * 购物车DAO接口
 * @author yjg
 * @version 2018-09-19
 */
@MyBatisDao
public interface ZlCartDao extends CrudDao<ZlCart> {
	//查询所有商品总价
	public Float goodsTotalPrice(ZlCart zlCart);
	//查询所有商品数量
	public Integer goodsTotalNum(ZlCart zlCart);
}