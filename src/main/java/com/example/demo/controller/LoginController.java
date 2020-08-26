package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.redis.UserEntityRepository;
import com.example.demo.service.impl.LoginServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    LoginServiceImpl loginServiceImpl;
    @Autowired
    UserListServiceImpl userListServiceImpl;

    @RequestMapping("clientLogin")
    public String clientLogin() {
        return "clientLogin";

    }

    @RequestMapping("quit")
    public String quit(HttpServletRequest request) {
        request.getSession().removeAttribute("userName");
        return "redirect:clientLogin";

    }

    //@RequestMapping("/loginservice")
    //public String loginservice(String userName, String passWord, HttpServletRequest request){
    //    boolean bl=	loginServiceImpl.loginuser(userName, passWord);
    //    if(bl){
    //        //将用户的全部信息查询出来
    //        UserEntity userEntity = userListServiceImpl.userlist(userName);
    //        String str="正常";
    //        if (!str.equals(userEntity.getUserState())){
    //            System.out.println("失败");
    //            //失败
    //            request.setAttribute("PHO", "该账户待审核!");
    //        }else {
    //            request.getSession().setAttribute("userName", userName);
    //            return "redirect:/index";
    //        }
    //
    //
    //    }else{
    //        //失败
    //        request.setAttribute("PHO", "用户名或密码错误!");
    //
    //    }
    //
    //    return "clientLogin";
    //}

    @Autowired
    public UserEntityRepository repository;

    @RequestMapping("/loginservicebyRedis")
    public String loginservicebyRedis(String userName,
                                      String passWord,
                                      HttpServletRequest request){

        // 从redis缓存中提取数据
        UserEntity userEntity=repository.findByUserNameAndPassWord(userName,passWord);
                // 如果redis缓存中没有，则从Mysql数据库中查询并放入Redis缓存中
        if(userEntity == null)
        {
            System.out.println("Redis中没有命中,查询MySQL中......");
            UserEntity userEntityfromMysql=new UserEntity();
            boolean bl=	loginServiceImpl.loginuser(userName, passWord);
            if(bl) {
                //将用户的全部信息查询出来
                userEntityfromMysql  = userListServiceImpl.userlist(userName);
                UserEntity save=repository.save(userEntityfromMysql);
                System.out.println("保存到redis： "+save.getUserName());
                request.getSession().setAttribute("userName", userName);
                userEntity=userEntityfromMysql;
            }else{
                System.out.println("MySQL中未找到该用户！");
                request.setAttribute("PHO", "用户名或密码错误!");
                return "clientLogin";

            }
        }
        String disable="禁用";
        String verify="待审核";
        if (verify.equals(userEntity.getUserState())){
            System.out.println("状态："+userEntity.getUserState());
            System.out.println("该账户待审核!");
            request.getSession().removeAttribute("userName");
            //失败
            request.setAttribute("PHO", "该账户待审核!");
            return "clientLogin";
        }else if (disable.equals(userEntity.getUserState())){
            System.out.println("状态："+userEntity.getUserState());
            System.out.println("该账户被禁用!");
            request.getSession().removeAttribute("userName");

            //失败
            request.setAttribute("PHO", "该账户被禁用!");
            return "clientLogin";
        }else {
            System.out.println("状态："+userEntity.getUserState());
            System.out.println("登录成功!");
            request.getSession().setAttribute("userName", userName);
            return "redirect:/index";
        }
    }

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @RequestMapping("/findPassword")
    public String findPassword(String userName,HttpServletRequest request) {
        System.out.println("userName: "+userName);
        UserEntity userEntityfromMysql  = userListServiceImpl.userlist(userName);
        System.out.println("getPassWord: "+userEntityfromMysql.getPassWord());
        System.out.println("getUserEmial: "+userEntityfromMysql.getUserEmial());
//hh
        String to=userEntityfromMysql.getUserEmial();
        String subject="找回密码";
        String text="您的密码是："+userEntityfromMysql.getPassWord();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            // 发送邮件
            mailSender.send(message);
            System.out.println("纯文本邮件发送成功");
        } catch (MailException e) {
            System.out.println("纯文本邮件发送失败 "+e.getMessage());
            e.printStackTrace();
        }



        return "clientLogin";

    }


}
