package com.example.demo.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2021000118651276";
    public static String RSA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCrGgkGP/ZmkDoBKU9srZ2fik7RccPFJT/sCL96ld9ckc2sfU+TgW3cBHhS181PdOpeVVBsQwtv6ZRUlNbp5KcBeeLGmcTBbB4B+JYYob7/qN4qL5dcefNBlIYpRqzzgwaLO5XroN3I0WnO/YnSq4UgmgPs7H5TYjVv4MdVjHNTawZj5YuIj2ZbRpHhvxegPUebCWVSoFe5egcvpSJhc5RSOeaxXmDMBcCrddDz9IwTg7IvyhJpVvXju2FuSJSXi39a3LtKZ1C4+3wO2aTfBkNecGr2lShUv/bbJy1ix1fL+w7qzlH9v4xAk7FknFd5J+Ljo699PWLoOBg7DkbtrVinAgMBAAECggEAf+tuFcsaR6HKi/W7mK/QaWe496OLTshDmw8LQPXt0UJ8t1nUdZ5NaTG6V41ZxO7PDgnQn7AcS+Yg7H9+kc1OAq+CmSJ7VHSllqmoZe//YRp6RqXgDtZpxHzrNM7PHRu3d77rngdd6PaPmZ46PVPYqwAasTY6V9LTyroNrWrdCd58RwTXXU+59wicTFmhlBnumQUuxuB5Q0+c0l+P+zEzCIIHzcXrlv+TQbcd+WzLGQoK4nI9HcGFSu729j22y1052QxZWKCr44hxnQtl/PzqlhlsYWEzJXc0O8vo28C3fLo98lmpye4QbEnqPJtR+7W0jgkoEW8CdmSX7uFyEVnq2QKBgQD1DRPXbOoH8LnBP3naa6LvmXty035mMXfMfIs1zs24atBXOQWX2+mYS7m2HPRITTm4Mu33oJUFStbkLPYBvr4EUtmIZg4ReAhCxk7fmn3/PI1O7LvD9RnFORYb/NheKWcNoqI6ISn4n85oJxh0hc0Z6KAjVC09MmJ8St5CBmqxkwKBgQCyvx2+UQRVYOhDWZlVMFI01o+IC8h9IP3lsSktv5c7y+Hq4mgbfYdv+SYfggv94MB8bYLiAZhO7G+XBdmpMBE8HNptc6eUeCNVQnnbkgWd5P9GIsNl6qygj537niI+tGluAyjmbo5H7zF6jKBXzaYYFzlfeYkCFd8QeEwPnCy5HQKBgQC2D9BFBnteCODJjdKHj97PatXH+w/29xvNhl6bTJmVQW6mzwuzxsRnzSMeBacMrKuMifI5P0pYU0ES58CijUd5yOArsb8UBX0Ft3ZUNOEVp7RJBiN+tHRADmi5xIIwe0xAWitzSr53JKviv+EweVRnwmhvtxOhWvTG6Yz1uvS8FwKBgQCc+IsVRC6rD9RIuRmXnzzCHB6mGbrycBOoL+F6xIL6RzzZugQ1CBL1eXZKOzlutX40hzNmaIIp5SbGGKQiGQ05nhkP/34OO0bELAG5mk2GcMDhBcxU5za8NgNGIYPvTx47N1kmq7YVY/Aur+pr0TRoeZSvzJpOE4j1/L7HAp6f3QKBgQCvdfWuLg2wsP0bGBVrrQ5nXhuhcIG3OqT19nl6PgsyY6348MOkcN1qvm2yJxGH66nevcDyJ6wAndOrC2HPHnpxCxCaVZJEaEzqyddesUrCD5eGxFLRySIPhLo4MujR5vTpkmj4nxzZGiodbosh8PTntjNhNC64yi+Pma8/FEGr/w==";
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
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAljoAQHLV9uH24oc4B8u0HvjBEyjdKOgW1OG0YFPTki2Q+kducOfLPf4i8R+53N9VPSIqEf45nhpNbYN7nWwXHwaRvlAaxOkNGeaOSEHzG0nVmJ+/LoY/eeh+xuwoi0D7J4QdTgl91M2yCC4kdx2EKxTS+OHMdYt27lXOkWtLO/76Vc1iVEPWFlb0sD2VBuj67Qr6qd4YzVvsNo6gBZietlsC+/lS/yS+FQ7zrELAJvFAvz2yCOT5KgEcqQFy1QFqRiy3gsIZe/9VTqb8SyiDE1+0ENzTGbccxyKVHomYDB9I3uK/eCb353UMG4ZSqyGKz3UlYdU9U4eB1WP8NjhTnQIDAQAB";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
