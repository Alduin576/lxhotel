package cn.hotel_lanxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PubController {

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

}
