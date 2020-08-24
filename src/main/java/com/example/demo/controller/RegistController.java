package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.RegisterServiceImpl;
import com.example.demo.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistController {

    @Autowired
    RegisterServiceImpl registerServiceImpl;
    @RequestMapping("register")
    public String register(HttpServletRequest request) {


        // 随机生成一个4位数验证码
        int num = GetUUID.yanzma();
        // 将int 转换为 String
        String yanzheng = "";
        yanzheng = String.valueOf(num);
        // 将验证码放入到session里面....暂时想不到其他办法
        request.getSession().setAttribute("yanzhengma", yanzheng);
        return "register";

    }

    @RequestMapping("registerOperation")
    public String registerOperation(UserEntity user,
                                    HttpServletRequest request,
                                    String yanzheng) {
        System.out.println("测试是否进入此方法");
        // 设置一个默认的用户ID UUID
        user.setUserID(GetUUID.getUUID());
        // 设置用户注册时 默认状态

        user.setUserState("待审核");
        // 设置注册用户的默认支付密码; (一开始忘记了)
        user.setUserPaypassword("123456");
        // 设置注册用户的默认头像
        user.setUserHand("userHand_Top/upload/MyHand.png");

        // 手机号码 正则表达式
        String phone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9])|(18[1,5-9])|(17[7,5-9]))\\d{8}$";
        Pattern regex = Pattern.compile(phone);
        Matcher matcher = regex.matcher(user.getUserPhone());
        boolean pho = matcher.matches();
        // 邮箱 正则表达式
        String email = "[a-zA-Z0-9_\\-\\.]+@(sina|qq|163)+(\\.(com|cn|org|edu|hk))";
        Pattern regexemail = Pattern.compile(email);
        Matcher matcheremial = regexemail.matcher(user.getUserEmial());
        boolean emial = matcheremial.matches();
        //验证用户账号
        String userNmae = "^[a-zA-Z]\\w{5,17}$";
        Pattern regexuserNmae = Pattern.compile(userNmae);
        Matcher matcheruserName = regexuserNmae.matcher(user.getUserName());
        boolean username = matcheruserName.matches();

        //获得验证码.....我是放入到session里的
        String yan = (String) request.getSession().getAttribute("yanzhengma");

        if (yanzheng.equals(yan)) {
            if (pho) {
                if (emial) {
                    if (username) {
                        boolean bl = registerServiceImpl.registerService(user);
                        if (bl) {
                            // 这里是注册成功
                            request.getServletContext().setAttribute("getUserName", user.getUserName());
                        } else {
                            request.setAttribute("fail", "注册失败,此用户已经被注册");
                            return "forward:/register";
                        }
                    } else {
                        request.setAttribute("fail", "注册失败,用户名输入错误");
                        return "forward:/register";
                    }
                } else {
                    request.setAttribute("fail", "注册失败,邮箱地址错误");
                    return "forward:/register";
                }

            } else {
                request.setAttribute("fail", "注册失败,手机号码错误");
                return "forward:/register";
            }
        } else {
            request.setAttribute("fail", "注册失败,验证码错误");
            return "forward:/register";
        }
        request.setAttribute("success", "注册成功,请等待管理员审核");
        return "forward:/register";
    }
    
}
