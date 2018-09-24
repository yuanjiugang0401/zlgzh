/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.page.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.weixin.util.GetWxOrderno;
import com.thinkgem.jeesite.modules.weixin.util.TopayServlet;
import com.thinkgem.jeesite.modules.weixin.util.WxUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zl.entity.ZlAddress;
import com.thinkgem.jeesite.modules.zl.entity.ZlCart;
import com.thinkgem.jeesite.modules.zl.entity.ZlOrder;
import com.thinkgem.jeesite.modules.zl.service.ZlAddressService;
import com.thinkgem.jeesite.modules.zl.service.ZlCartService;
import com.thinkgem.jeesite.modules.zl.service.ZlOrderService;
import com.thinkgem.jeesite.modules.zl.service.ZlUserService;

/**
 * 订单
 *
 * @author yjg
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/")
public class OrderController extends BaseController {
    @Autowired
    private ZlOrderService zlOrderService;
    @Autowired
    private ZlAddressService zlAddressService;
    @Autowired
    private ZlCartService zlCartService;


    /**
     * 订单列表页面
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "page/orderList")
    public String orderList(HttpSession session, Model model) {
        ZlOrder zlOrder = new ZlOrder();
        // 正式环境
        //zlOrder.setOppenId(getOppen_id(session));
        // 测试数据
        zlOrder.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
        List<ZlOrder> list = zlOrderService.findList(zlOrder);
        model.addAttribute("orderList", list);
        return "modules/page/orderList";
    }

    /**
     * 购物车到订单结算页面
     *
     * @param session
     * @param zlOrder
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "page/cartOrder")
    public String cartOrder(HttpSession session, ZlOrder zlOrder,
                            HttpServletRequest request, HttpServletResponse response, Model model) {

        ZlAddress zlAddress = new ZlAddress();
        ZlAddress address = null;
        if (zlOrder.getAddrId() == null || zlOrder.getAddrId().equals("")) {
            //查找默认地址
            zlAddress.setIsDefault("1");
        } else {
            zlAddress.setId(zlOrder.getAddrId());
        }
        zlAddress.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
        address = zlAddressService.selectByIsDefault(zlAddress);

        String goodsId = zlOrder.getGoodsId();
        ZlCart zlCart = new ZlCart();
        zlCart.setGoodsId(goodsId);
        zlCart.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
        List<ZlCart> list = zlCartService.findList(zlCart);
        //总价格
        Float totalPrice = zlCartService.goodsTotalPrice(zlCart);
        model.addAttribute("pay_way", "1");
        model.addAttribute("goodsId", goodsId);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("zlAddress", address);
        model.addAttribute("cartList", list);
        return "modules/page/payment";
    }


    /**
     * 付款后微信返回信息，更改订单状态
     */
    @RequestMapping(value = "/page/noticeOrder")
    public void noticeOrder(HttpServletRequest request) {
        ZlOrder zlOrder=new ZlOrder();
        String xmlStr = WxUtil.getWxXml(request);
        Map map2 = GetWxOrderno.doXMLParse(xmlStr);
        String return_code = (String) map2.get("return_code");
        String order_id = (String) map2.get("out_trade_no");
        zlOrder.setOrderId(order_id);

        ZlOrder order=zlOrderService.get(zlOrder);
        logger.info("微信返回 ---->"+xmlStr);
        if (order.getStatus().equals("0")) {
            if (return_code.equals("SUCCESS")) {
                order.setStatus("1");
                order.setIsNewRecord(false);
                zlOrderService.save(order);
            }
        }
    }

    /**
     * 订单支付页面
     *
     * @param session
     * @param zlOrder
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "page/payOrder")
    public String payOrder(HttpSession session, ZlOrder zlOrder,
                           HttpServletRequest request, HttpServletResponse response, Model model) {
        //结果输出map
        Map<String, Object> map = new HashMap<String, Object>();
        int rs=0;
        String message="";
        //首先获取购物车中确认购买的商品
        String goodsId = zlOrder.getGoodsId();
        if (StringUtils.isNotEmpty(goodsId)) {
            ZlCart zlCart = new ZlCart();
            zlCart.setGoodsId(goodsId);
            zlCart.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");

            //所有的商品
            List<ZlCart> list = zlCartService.findList(zlCart);
            //总价格
            Float totalPrice = zlCartService.goodsTotalPrice(zlCart);
            //收货地址
            ZlAddress zlAddress = zlAddressService.get(new ZlAddress(zlOrder.getAddrId()));
            //商品总数量
            Integer goodsTotalNum = zlCartService.goodsTotalNum(zlCart);
            //订单编号
            zlOrder.setOrderId(IdGen.getOrderId());
            //订单总金额
            zlOrder.setGoodsTotalPrice(totalPrice);
            //购买者
            zlOrder.setOppenId("obeSL1UNxLBxm4KhTeYEppRyX_sk");
            //商品名称
            zlOrder.setGoodsName(Collections3.extractToString(list, "goodsName", ","));
            //商品编号
            zlOrder.setGoodsId(Collections3.extractToString(list, "goodsId", ","));
            //商品图片
            zlOrder.setGoodsPic(Collections3.extractToString(list, "goodsPic", ","));
            //商品规格
            zlOrder.setGoodsSpe(Collections3.extractToString(list, "goodsSpe", ","));
            //商品数量
            zlOrder.setGoodsNum(Collections3.extractToString(list, "goodsNum", ","));
            //商品价格
            zlOrder.setGoodsPrice(Collections3.extractToString(list, "goodsPrice", ","));
            //商品总数量
            zlOrder.setGoodsTotalNum(goodsTotalNum);
            //收获地址
            zlOrder.setAddrName(zlAddress.getAddrUser() + "  " + zlAddress.getAddrTel() + "  " + zlAddress.getAddrCity() + "  " + zlAddress.getAddrName());
            //商品状态
            zlOrder.setStatus("0"); //待支付状态

            //首先添加到购物车

            zlOrder.setIsNewRecord(true);
            zlOrderService.save(zlOrder);
            if(Integer.parseInt(zlOrder.getId())>0){
                //移除购物车
                zlCartService.deleteByIds(zlCart);
                //表示为微信支付
                if (zlOrder.getPayType().equals("1")) {
                    map = TopayServlet.getPackage(zlOrder, request, response);
                } else {
                    //表示账户余额支付
                }
                rs=1;
                message="支付成功！";
            }else{
                message = "提交订单失败！";
                rs=0;

            }
            map.put("message",message);
            map.put("rs_code", rs);
        }else{
            map.put("message","购买商品失败");
            map.put("rs_code", rs);
        }
        return renderString(response,map);
    }


}