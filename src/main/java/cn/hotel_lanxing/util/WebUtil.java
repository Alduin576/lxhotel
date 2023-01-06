package cn.hotel_lanxing.util;

import cn.hotel_lanxing.domain.Authority;
import cn.hotel_lanxing.domain.Customer;
import cn.hotel_lanxing.domain.User;
import cn.hotel_lanxing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class WebUtil {

    public static String getUsername(){
        //获取应用上下文
        SecurityContext context = SecurityContextHolder.getContext();
        //获取用户相关信息
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }
}
