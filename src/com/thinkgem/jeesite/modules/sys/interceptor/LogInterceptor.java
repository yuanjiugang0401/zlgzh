/**
 * Copyright &copy; 2012-2014 <a href="http://www.huayingsoft.com">JGGFrame</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.interceptor;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.zl.dao.ZlWxSettingDao;
import com.thinkgem.jeesite.modules.zl.entity.ZlWxSetting;

/**
 * 日志拦截器
 * 
 * @author ThinkGem
 * @version 2014-8-19
 */
public class LogInterceptor extends BaseService implements HandlerInterceptor {

	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
			"ThreadLocal StartTime");
	private static final String[] IGNORE_URI = { "/gen/", "/sys/", "/cms/",
			"/oa/", "/admin/", "/userInsert", "/oauth2/",
			"/noticeOrder.html", "a?login","/zl","/page"};
	//"page/index"
	Map<String, Object> map = new HashMap<String, Object>();
	private Logger log = Logger.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (logger.isDebugEnabled()) {
			long beginTime = System.currentTimeMillis();// 1、开始时间
			startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
			logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat(
					"hh:mm:ss.SSS").format(beginTime), request.getRequestURI());
		}
		HttpSession session = request.getSession();
		boolean flag = false;
		flag = session.getAttribute("oppen_id") != null ? true : false;
		String url = (request.getRequestURL() + "?" + request.getQueryString())
				.toString();
		System.out.println(">>>: " + url);
		log.info("url>>>: " + url);
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			ZlWxSettingDao zlWxSettingDao = SpringContextHolder
					.getBean(ZlWxSettingDao.class);
			ZlWxSetting zlWxSetting = zlWxSettingDao.get("1");
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase(
							"XMLHttpRequest")) { // 如果是ajax请求响应头会有x-requested-with
				map.put("rs_code",1005);
				log.info("ajax -- 进入--->>>");
			    response.getWriter().write(JsonMapper.getInstance().toJson(map));
			} else {
				log.info("不是ajax -- 进入--->>>");
				log.info("wxSetting.getAppid()=="
						+ zlWxSetting.getAppid());
				log.info("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
								+ zlWxSetting.getAppid()
								+ "&redirect_uri="
								+ zlWxSetting.getLink()
								+ "/a/page/userInsert?url="
								+ url
								+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
				response
						.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
								+ zlWxSetting.getAppid()
								+ "&redirect_uri="
								+ zlWxSetting.getLink()
								+ "/a/page/userInsert?url="
								+ url
								+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
			}
		}
		log.info("最终 flag=="+flag);
		// flag=true ;
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// 保存日志
		LogUtils.saveLog(request, handler, ex, null);

		// 打印JVM信息。
		if (logger.isDebugEnabled()) {
			long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
			long endTime = System.currentTimeMillis(); // 2、结束时间
			logger
					.debug(
							"计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
							new SimpleDateFormat("hh:mm:ss.SSS")
									.format(endTime),
							DateUtils.formatDateTime(endTime - beginTime),
							request.getRequestURI(),
							Runtime.getRuntime().maxMemory() / 1024 / 1024,
							Runtime.getRuntime().totalMemory() / 1024 / 1024,
							Runtime.getRuntime().freeMemory() / 1024 / 1024,
							(Runtime.getRuntime().maxMemory()
									- Runtime.getRuntime().totalMemory() + Runtime
									.getRuntime().freeMemory()) / 1024 / 1024);
		}

	}

}
