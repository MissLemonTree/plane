package com.yinggu.plane.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.yinggu.plane.common.AlipayFaceToFace;
import com.yinggu.plane.pojo.ZFBFaceToFaceModel;
import com.yinggu.plane.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName AliPayController
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/12 9:07
 * Version 1.0
 */
@Controller
@RequestMapping("/AlipayFaceToFaceController")
public class AliPayController {


    @RequestMapping("/toPay")
    public String toPay(){
        return "alipay";
    }

    /**
     * 支付宝预约下单
     * 用于接受前端请求 返回给前端二维码地址和商户唯一订单编号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ZFBPreorderAction")
    @ResponseBody
    public Map<String,Object> ZFBPreorderAction(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resultMap=new HashMap<String, Object>();
        try {
            CommonUtils commonUtils=new CommonUtils();
            //(必填)商户唯一订单编号
            String outTradeNo= CommonUtils.getUuid();
            // (必填) 订单标题，粗略描述用户的支付目的。如“喜士多（浦东店）消费”
            String subject ="毛毛消费（中国）";
            // (必填) 订单总金额，单位为元，不能超过1亿元
            String totalAmount = "0.01";
            //（必填）支付成功支付支付宝异步通知的接口地址
            String NotifyUrl=commonUtils.getZFBinfoValue("NotifyUrl");
            //将参数放入实体对象中
            ZFBFaceToFaceModel zfbFaceToFaceModel=new ZFBFaceToFaceModel();
            zfbFaceToFaceModel.setOutTradeNo(outTradeNo);
            zfbFaceToFaceModel.setSubject(subject);
            zfbFaceToFaceModel.setTotalAmount(totalAmount);
            zfbFaceToFaceModel.setNotifyUrl(NotifyUrl);
            //支付宝预下单
            String json= AlipayFaceToFace.ZFBPreorder(zfbFaceToFaceModel);
            //解析json数据
            JSONObject jsonObject=JSONObject.parseObject(json);
            //得到alipay_trade_precreate_response数据后再强转JSONObject
            JSONObject jsonobj_two=(JSONObject)jsonObject.get("alipay_trade_precreate_response");
            //再通过jsonobj_two获取到二维码地址
            String qrcode=jsonobj_two.get("qr_code").toString();

            resultMap.put("qrcode",qrcode);
            resultMap.put("outTradeNo",outTradeNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    @RequestMapping("/findZFB_tradeAction")
    @ResponseBody
    public Map<String,Object> findZFB_tradeAction(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> resultMap=new HashMap<String, Object>();
        try {
            //(必填)商户唯一订单编号
            String outTradeNo=request.getParameter("outTradeNo");
            ZFBFaceToFaceModel zfbFaceToFaceModel=new ZFBFaceToFaceModel();
            zfbFaceToFaceModel.setOutTradeNo(outTradeNo);
            //查询交易状态
            String json=AlipayFaceToFace.findZFB_trade(zfbFaceToFaceModel);
            System.out.println(json);
            JSONObject jsonObject=JSONObject.parseObject(json);
            JSONObject jsonobj_two=(JSONObject)jsonObject.get("alipay_trade_query_response");
            //网关返回码,详见文档 https://opendocs.alipay.com/open/common/105806
            String ZFBCode=(String)jsonobj_two.get("code");
            //业务返回码
            String ZFBSubCode=(String)jsonobj_two.get("sub_code");
            //业务返回码描述
            String sub_msg=(String)jsonobj_two.get("sub_msg");
            //交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
            String trade_status=(String)jsonobj_two.get("trade_status");
            if (ZFBCode.equals("40004") && ZFBSubCode.equals("ACQ.TRADE_NOT_EXIST")) {
                //订单未创建（用户未扫码）
                resultMap.put("code", ZFBCode);
                resultMap.put("data", "用户未扫码");
            } else if (ZFBCode.equals("10000") && trade_status.equals("WAIT_BUYER_PAY")) {
                //订单已经创建但未支付（用户扫码后但是未支付）
                resultMap.put("code", ZFBCode);
                resultMap.put("data", "non-payment");
            } else if (ZFBCode.equals("10000") && (trade_status.equals("TRADE_SUCCESS") || trade_status.equals("TRADE_FINISHED"))) {
                //判断ZFBCode是否等于”10000“ 并且 trade_status等于TRADE_SUCCESS（交易支付成功）或者 trade_status等于TRADE_FINISHED（交易结束，不可退款）
                //订单已支付（用户扫码完成并且支付成功之后）
                resultMap.put("code", ZFBCode);
                resultMap.put("data", "yes-payment");
            } else {
                resultMap.put("code", ZFBCode);
                resultMap.put("data", sub_msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping("/ZFBcallback")
    public void ZFBcallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CommonUtils commonUtils=new CommonUtils();
            //支付宝公钥
            String alipay_public_key=commonUtils.getZFBinfoValue("alipay_public_key");
            PrintWriter out;
            out = response.getWriter();
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            //循环遍历支付宝请求过来的参数存入到params中
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //异步验签：切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            boolean flag = AlipaySignature.rsaCheckV1(params, alipay_public_key, "utf-8","RSA2");
            if (flag){
                //说明是支付宝调用的本接口
                if (params.get("trade_status").equals("TRADE_SUCCESS") || params.get("trade_status").equals("TRADE_FINISHED")) {
                    System.out.println("收到回调结果，用户已经完成支付");
                    /***
                     * 这里写您的业务逻辑代码
                     */
                    out.write("success");
                }
            }else {
                //验签失败该接口被别人调用
                out.write("支付宝异步回调验签失败，请留意");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
