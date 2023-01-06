package cn.hotel_lanxing.controller;

import cn.hotel_lanxing.domain.Customer;
import cn.hotel_lanxing.domain.User;
import cn.hotel_lanxing.service.CustomerService;
import cn.hotel_lanxing.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/to_personalIndex")
    public String toPersonIndex(Model model){
        //使用静态方法获取用户名
        User.USERNAME = WebUtil.getUsername();
        Boolean isAdmin = User.USERNAME.equals("admin");
        Boolean hasCustomer = false;
        Customer customer = getCustomer_C();
        if(customer!=null){
            hasCustomer = true;
        }
        model.addAttribute("username",User.USERNAME);
        model.addAttribute("idAdmin",isAdmin);
        model.addAttribute("hasCustomer",hasCustomer);
        model.addAttribute("customer",customer);
        return "personal/personIndex";
    }

    /*
    定义方法 根据用户名 进行多表查询 获取到用户相对应的客户信息
     */
    public Customer getCustomer_C(){
        //获取到客户
        Customer customer = customerService.getCustomer(WebUtil.getUsername());
        if(customer!=null){
            return customer;
        }else{
            return null;
        }
    }



}
