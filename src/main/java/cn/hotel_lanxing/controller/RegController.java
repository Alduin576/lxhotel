package cn.hotel_lanxing.controller;

import cn.hotel_lanxing.domain.User;
import cn.hotel_lanxing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

/**
 * 注册 控制器
 */
@Controller
@RequestMapping("/reg")
public class RegController {
    //引入依赖
    @Autowired
    private UserService userService;


    // 进入注册页面
    @GetMapping("/toReg")
    public String toReg(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "loginRegister/user_register";
    }

    //处理注册
    /*
     * 使用加密类对密码进行加密
     * 注册成功后跳转到登录界面
     */
    @PostMapping("/doReg")
    public String doReg(String username,String password,Model model){
        //可直接获取username和password，只要与domain类属性同名即可
        //获取用户信息
        User user = userService.getUser(username);
        if(user != null){
            //不为空说明用户名可以在数据库查找到信息，重复
            boolean isDuplicated = true;
            model.addAttribute("isDuplicated",isDuplicated);
            return "loginRegister/user_register";
        }
        userService.addUser(username,password);
        return "redirect:/startLogin";
    }



}
