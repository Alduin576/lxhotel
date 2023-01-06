package cn.hotel_lanxing.controller;

import cn.hotel_lanxing.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
public class LoginController {

    @GetMapping("/startLogin")
    //映射路径是用户跳转到登录
    public String toLoginPage(Model model){
        User loginUser = new User();
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "loginRegister/user_login";
    }
}
