package com.thinkgem.jeesite.modules.weixin.util;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.zl.dao.ZlWxSettingDao;
import com.thinkgem.jeesite.modules.zl.entity.ZlOrder;
import com.thinkgem.jeesite.modules.zl.entity.ZlWxSetting;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TopayServlet {


    public static Map<String, Object> getPackage(ZlOrder zlOrder, HttpServletRequest request, HttpServletResponse response) {
        //结果输出map
        Map<String, Object> map = new HashMap<String, Object>();

        //微信相关配置
        ZlWxSettingDao zlWxSettingDao = SpringContextHolder
                .getBean(ZlWxSettingDao.class);
        ZlWxSetting zlWxSetting = zlWxSettingDao.get("1");
        //商品描述
        String body = zlOrder.getGoodsName();
        if (body.contains(",")) {
            body = body.replace(",", "");
        }
        //商品订单号
        String orderNo = zlOrder.getOrderId();
        //总金额
        float money = zlOrder.getGoodsTotalPrice();
        int price = (int) (money * 100);
        String totalFee = 1+"";
        String openId = zlOrder.getOppenId();
        //回调函数
        String notify_url = zlWxSetting.getLink() + "/a/page/noticeOrder/";
        //当前时间
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 商户号
        String mch_id = zlWxSetting.getPartner();
        // 设备号 非必输
        String device_info = "";
        // 随机数
        String nonce_str = strReq;
        // 附加数据
        String attach = "";
        // 商户订单号
        String out_trade_no = orderNo;
        String spbill_create_ip = request.getRemoteAddr();
        String trade_type = "JSAPI";
        String openid = openId;

        String appid=zlWxSetting.getAppid();


// 非必输
        // String product_id = "";
        SortedMap<String, Object> packageParams = new TreeMap<String, Object>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);

        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        packageParams.put("openid", openid);
        RequestHandler reqHandler = new RequestHandler(
                request,
                response);
        reqHandler.init(zlWxSetting.getAppid(), zlWxSetting.getAppsecret(), zlWxSetting.getPartnerkey());
        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>"
                + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>"
                + "<attach>" + attach + "</attach>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" +
                // 金额，这里写的1 分到时修改
                "<total_fee>" + totalFee + "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip
                + "</spbill_create_ip>" + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>" + trade_type
                + "</trade_type>"
                + "<openid>" + openid + "</openid>"
                + "</xml>";
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";;
        String prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
        SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
        String timestamp = Sha1Util.getTimeStamp();
        String packages = "prepay_id=" + prepay_id;
        finalpackage.put("appId", appid);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonce_str);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        String finalsign = reqHandler.createSign(finalpackage);

        map.put("appId", appid);
        map.put("timeStamp", timestamp);
        map.put("nonceStr", nonce_str);
        map.put("package", packages);
        map.put("signType", "MD5");
        map.put("paySign", finalsign);
        map.put("prepay_id", prepay_id);
        return map;
    }

}
