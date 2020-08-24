package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.redis.UserEntityRepository;
import com.example.demo.service.impl.UpdateServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UpdateController {

    @Autowired
    UpdateServiceImpl updateServiceImpl;
    @Autowired
    UserListServiceImpl userListServiceImpl;
    @Autowired
    UserEntityRepository repository;

    @RequestMapping("Update")
    public String Update(){
        return "Update_login_password";
    }

    @RequestMapping("Update_login_password")
    public ModelAndView Update_login_password(String passWord, String newpassWord, String newpassWord2,
                                              HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        UserEntity userEntityfromMysql  = userListServiceImpl.userlist(userName);
        repository.delete(userEntityfromMysql);
        Map model = new HashMap();
        if (passWord != null || newpassWord != null || newpassWord2 != null) {
            if (newpassWord.equals(newpassWord2)) {
                boolean bl = updateServiceImpl.Update_login_password(userName, passWord, newpassWord);
                if (bl) {

                    request.getSession().removeAttribute("userName");
                    request.setAttribute("changeSuccessfully", "修改密码成功，请重新登陆!");

                    System.out.println("修改成功");
                    return new ModelAndView("clientLogin", model);
                } else {
                    request.setAttribute("PassWordErro", "对不起,旧密码输入有误!");
                    model.put("PassWordErro", "对不起,旧密码输入有误!");
                }
            } else {
                request.setAttribute("PassWordErro", "两次密码输入有误!");
                model.put("PassWordErro", "对不起,两次密码输入有误!");

            }
        }
        System.out.println("修改失败");
        return new ModelAndView("forward:/user", model);
    }

    @RequestMapping("Update_email")
    public String Update_email(HttpServletRequest request, String emial, String newemial) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || emial != null || newemial != null) {
            boolean bl = updateServiceImpl.Update_login_Emial(userName, emial, newemial);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro2", "对不起,原邮箱输入错误!");
            }
        } else {
            request.setAttribute("PassWordErro", "输入不能为空!");
        }

        return "Update_email";

    }

    @RequestMapping("Update_Phone")
    public String Update_Phone(HttpServletRequest request, String userPhone, String newuserPhone) {
        // 得到session里面的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || userPhone != null || newuserPhone != null) {
            boolean bl = updateServiceImpl.Update_login_Phone(userName, userPhone, newuserPhone);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro3", "对不起,原手机号码错误!");
            }
        } else {
            request.setAttribute("PassWordErro3", "输入不能为空!");
        }
        return "Update_Phone";

    }

    @RequestMapping("Update_userHand")
    public String userHand(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        String fileName = (String) request.getSession().getAttribute("fileName");
        UserEntity user = userListServiceImpl.userlist(userName);

        String userHand = user.getUserHand();
        String newuserHand = fileName;

        boolean bl = updateServiceImpl.Update_login_hand(userName, userHand, newuserHand);

        if (bl) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        return "redirect:/user";

    }

    @RequestMapping("Update_userInformation")
    public String Update_userInformation(HttpServletRequest request) {
        return "redirect:/user";

    }


}
