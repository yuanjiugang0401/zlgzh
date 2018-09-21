package com.thinkgem.jeesite.modules.weixin.util;


import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.zl.dao.ZlWxSettingDao;
import com.thinkgem.jeesite.modules.zl.entity.ZlWxSetting;

public class StringUtil {
	public static ZlWxSetting getSetting() {
		ZlWxSettingDao zlWxSettingDao = SpringContextHolder
				.getBean(ZlWxSettingDao.class);
		ZlWxSetting zlWxSetting = zlWxSettingDao.get("1");
		return zlWxSetting;
	}

	static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";// 创建菜单
	static String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code"; // 网页授权获取用户信息接口
	static String token_url2 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";// 全局accesstoken接口
	static String template_id_1 = "C6YRjqsvDLYFuVQbVHfAWKWPbXD8Ca_lSwiXG8cQQNY"; // 订单支付成功信息推送模板
	static String template_id_2 = "tjqPjlrB1vbXatR7_HhEefzjG1UNbacVTotD85J_ZR8	"; // 商品已发出通知
}
