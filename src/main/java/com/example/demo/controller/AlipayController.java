package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.example.demo.config.AlipayConfig;
import com.example.demo.entity.AlipayEntity;
import com.example.demo.utils.AlipayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class AlipayController {
    /**
     * 支付宝完成回调页面(不可信回调)
     */
    @GetMapping("/return")
    @ResponseBody
    private String alipayReturn(HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> handleParams = AlipayUtil.handleParams(parameterMap);

        // 这里的校验没有多大的意思,不可信,直接获取out_trade_no跳转到对应的payed controller也可
        boolean rsaCheck = AlipayUtil.rsaCheck(handleParams);
        if (rsaCheck){
            System.out.println("验证通过");
        }else {
            System.out.println("验证失败");
        }

        // 获取订单号
        String out_trade_no = handleParams.get("out_trade_no");
        System.out.println("out_trade_no:" + out_trade_no);
        // 这里一般都是 重定向 payed的controller, 然后携带对应的信息如:return "redirect:/alipay/success?out_trade_no=" + out_trade_no;
        // payed的controller根据out_trade_no获取支付结果,并且给出页面提示

        return "支付完成";
    }


    /**
     * 支付宝完成结果异步的回调(可信回调)
     * @param request
     */
    @PostMapping("/notify")
    @ResponseBody
    private String alipayNotify(HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> handleParams = AlipayUtil.handleParams(parameterMap);

        boolean rsaCheck = AlipayUtil.rsaCheck(handleParams);
        if (rsaCheck){
            System.out.println("验证通过");

            // 处理业务逻辑,更改支付状态等骚操作
            // ...
        }else {
            System.out.println("验证失败");
        }
        return rsaCheck ? "success" : "failure";
    }
        @RequestMapping("/pay/phone")
    public String pay() {

        AlipayEntity AlipayEntity = new AlipayEntity();
        // String out_trade_no = UUID.randomUUID().toString().replace("-", "");
        String out_trade_no = AlipayUtil.get().nextId() + "";
        System.out.println("out_trade_no:" + out_trade_no);
        // 设置订单单号,需要保证唯一性
        AlipayEntity.setOut_trade_no(out_trade_no);
        // 设置支付金额
        AlipayEntity.setTotal_amount("0.01");
        // 设置支付标题
        AlipayEntity.setSubject("houyu-test-title");
        // 设置订单有效时长(30分钟)
        AlipayEntity.setTimeout_express("30m");
        AlipayEntity.setProduct_code("QUICK_WAP_WAY");

        // 对象转为json字符串
        String json = JSONObject.toJSONString(AlipayEntity);

        // 建立连接
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

        // 创建请求
        AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();

        // 设置异步通知地址
        alipayTradeWapPayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 设置对调地址,就是说支付成功之后回调你的页面,你可以继续进行你的业务操作,但是这个是不可信任的,需要根据notify_url这边的回调确定支付是否成功
        alipayTradeWapPayRequest.setReturnUrl(AlipayConfig.return_url);

        // 封装请求支付信息
        alipayTradeWapPayRequest.setBizContent(json);

        String pageString;
        try {
            pageString = client.pageExecute(alipayTradeWapPayRequest).getBody();
        } catch (AlipayApiException e) {
            pageString = "request aliapy has error";
            e.printStackTrace();
        }
        return pageString;
    }
    @RequestMapping("/pay")
    public   void   doPost (HttpServletRequest httpRequest, HttpServletResponse httpResponse)   throws ServletException, IOException {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( AlipayConfig.return_url );
        alipayRequest.setNotifyUrl( AlipayConfig.notify_url ); //在公共参数中设置回跳和通知地址
        AlipayEntity AlipayEntity = new AlipayEntity();
        // String out_trade_no = UUID.randomUUID().toString().replace("-", "");
        String out_trade_no = AlipayUtil.get().nextId() + "";
        System.out.println("out_trade_no:" + out_trade_no);
        // 设置订单单号,需要保证唯一性
        AlipayEntity.setOut_trade_no(out_trade_no);
        // 设置支付金额
        AlipayEntity.setTotal_amount("10");
        // 设置支付标题
        AlipayEntity.setSubject("购买VIP服务");
        // 设置订单有效时长(30分钟)
        AlipayEntity.setTimeout_express("30m");
        AlipayEntity.setProduct_code("FAST_INSTANT_TRADE_PAY");
        //备注
        AlipayEntity.setBody("购买VIP服务");

        AlipayEntity.setPassback_params("merchantBizType%3d3C%26merchantBizNo%3d2016010101111");
        // 对象转为json字符串
        String json = JSONObject.toJSONString(AlipayEntity);

        alipayRequest.setBizContent(json); //填充业务参数*/
        String form= "" ;
        try  {
            form = client.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset="  + AlipayConfig.CHARSET);
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
