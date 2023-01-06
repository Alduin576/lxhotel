package cn.hotel_lanxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //进入管理员首页
    @GetMapping("/toAdmin_index")
    public String toIndex(){
        return "/admin/admin_index";
    }

}
