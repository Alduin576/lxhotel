package cn.hotel_lanxing.controller;

import cn.hotel_lanxing.domain.Customer;
import cn.hotel_lanxing.domain.User;
import cn.hotel_lanxing.service.CustomerService;
import cn.hotel_lanxing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 完成客户 功能模块
 * 填写用户信息（插入语句）
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

    @PostMapping("/doFill")
    /*
    完成用户填写客户信息的持久化
     */
    public String doFill(Customer customer){
        customerService.fillCustomerInfo(customer);
        return "redirect:/to_personalIndex";
    }

    /*
    完成用户修改 用户信息和客户信息
     */
    @PostMapping("/doUpdate")
    /*
    按照逻辑来应该先修改客户信息,因为客户id来源于用户名去查找的客户的信息,先修改用户必导致客户信息找不到
     */
    public String doUpdate(String username, String password, String telephone, String email){
        //先对客户进行修改
        //首先获取到相对应的客户
        //参数为当前登录的用户,保存着的静态变量USERNAME
        Customer customer = getCustomer();
        //取出customer中的id
        int id = customer.getId();
        //进行修改  telephone和email对应表单中的数据
        int customer_result = customerService.updateCustomer(telephone,email,id);
        //再对用户进行修改
        int user_result = userService.updateUser(username,password,User.USERNAME);
        if(customer_result>0 && user_result>0){
            //修改成功则到登录界面进行重新登录
            return "redirect:/startLogin";
        }else{
            return "";
        }
    }

    /*
    定义方法 根据用户名 进行多表查询 获取到用户相对应的客户信息
     */
    public Customer getCustomer(){
        //获取到客户
        Customer customer = customerService.getCustomer(User.USERNAME);
        if(customer!=null){
            return customer;
        }else{
            return null;
        }
    }


}
