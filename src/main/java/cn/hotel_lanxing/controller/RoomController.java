package cn.hotel_lanxing.controller;


import cn.hotel_lanxing.domain.Customer;
import cn.hotel_lanxing.domain.Room;
import cn.hotel_lanxing.domain.RoomOrder;
import cn.hotel_lanxing.domain.User;
import cn.hotel_lanxing.service.CustomerService;
import cn.hotel_lanxing.service.RoomOrderService;
import cn.hotel_lanxing.service.RoomService;
import cn.hotel_lanxing.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理客房的controller类
 * 其主要要实现的功能：预定客房、显示每个房间的状态、加入个人订单
 */

@Controller
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomService roomService;
    //引入CustomerService
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RoomOrderService roomOrderService;
    private String currentUsername = null;


    @GetMapping("/service/{type}/{path}")
    /*
    根据需求可能会需要用到context来获取到当前登录用户的信息
     */
    public String roomIndex(@PathVariable String type,@PathVariable String path){
        return "service/"+type+"/"+path;
    }

    @ResponseBody
    @GetMapping("/room/getAll")
    public List<Room> getAllRoom(){
        return this.getAllRooms();
    }

    @GetMapping("/to_roomIndex")
    public String toRoomIndex(Model model){
        //定义变量接收所有房间
        List<Room> rooms = this.getAllRooms();

        model.addAttribute("roomList",rooms);
        /*
        获取订单信息
         */
        //判断该用户是否有客户信息
        boolean hasCustomerInfo = false;
        if(customerService.getCustomer(WebUtil.getUsername())!=null){
            hasCustomerInfo = true;
        }
        List<RoomOrder> roomOrders = roomOrderService.getRoomOrderByName(customerService.getCustomerName());
        model.addAttribute("roomOrderList",roomOrders);
        model.addAttribute("hasCustomerInfo",hasCustomerInfo);
        return "service/room/roomIndex";
    }

    @GetMapping("/to_createRoom/{id}")
    public String toRoomCreate(Model model,@PathVariable("id")Integer id){
        /*
        通过获取的id来获取到room的number和price
         */
        //使用id查询到room
        Room room = roomService.getRoom(id);
        if(room != null){
            //获取number 和 price并传值
            model.addAttribute("number",room.getNumber());
            model.addAttribute("price","￥"+room.getPrice());
        }
        //根据当前登录的用户名查询到 客户信息
        boolean hasCustomerInfo = false;
        currentUsername = WebUtil.getUsername();
        User.USERNAME = currentUsername;
        Customer customer = customerService.getCustomer(User.USERNAME);
        if(customer != null){
            hasCustomerInfo = true;
        }
        model.addAttribute("hasCustomerInfo",hasCustomerInfo);
        model.addAttribute("CustomerInfo",customer);
        //跳转到创建开房界面
        return "service/room/createRoom";
    }

    @GetMapping("/to_preserveRoom/{id}")
    public String toPreserveRoom(Model model,@PathVariable("id")Integer id){
        /*
        通过获取的id来获取到room的number和price
         */
        //使用id查询到room
        Room room = roomService.getRoom(id);
        if(room != null){
            //获取number 和 price并传值
            model.addAttribute("number",room.getNumber());
            model.addAttribute("price","￥"+room.getPrice());
        }
        //根据当前登录的用户名查询到 客户信息
        boolean hasCustomerInfoInPreserve = false;
        currentUsername = WebUtil.getUsername();
        Customer customer = customerService.getCustomer(currentUsername);
        if(customer != null){
            hasCustomerInfoInPreserve = true;
        }
        model.addAttribute("hasCustomerInfoInPreserve",hasCustomerInfoInPreserve);
        model.addAttribute("CustomerInfo",customer);
        //跳转到预订房间界面
        return "service/room/preserveRoom";

    }
    //获取到所有的房间
    public List<Room> getAllRooms(){
        List<Room> rooms = roomService.getAllRooms();
        return rooms;
    }




}
