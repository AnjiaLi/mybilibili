package com.example.demo.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "";
    public static String RSA_PRIVATE_KEY = "";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.baidu.com";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.baidu.com";
    // 请求网关地址,沙箱是:https://openapi.alipaydev.com/gateway.do
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥(在应用中可以获取)
    public static String ALIPAY_PUBLIC_KEY ="";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
