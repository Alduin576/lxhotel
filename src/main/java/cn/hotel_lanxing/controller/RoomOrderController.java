package cn.hotel_lanxing.controller;

import cn.hotel_lanxing.domain.Customer;
import cn.hotel_lanxing.domain.Room;
import cn.hotel_lanxing.domain.RoomOrder;
import cn.hotel_lanxing.repository.RoomOrderRepository;
import cn.hotel_lanxing.service.CustomerService;
import cn.hotel_lanxing.service.RoomOrderService;
import cn.hotel_lanxing.service.RoomService;
import cn.hotel_lanxing.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roomOrder")
/**
 * 订房功能  订单功能模块  控制层
 */
public class RoomOrderController {
    //引入roomOrderService
    @Autowired
    private RoomOrderService roomOrderService;
    //引入 roomService
    @Autowired
    private RoomService roomService;
    //引入 customerService
    @Autowired
    private CustomerService customerService;


    //------处理下单
    //提交订单信息  进行的是插入操作
    //提交成功后跳转到 我的订单信息板
    @PostMapping("/doOrder")
    public String doOrder(RoomOrder roomOrderForm){
        boolean isSuccess = roomOrderService.addRoomOrder(roomOrderForm);
        return "redirect:/to_roomIndex";
    }

    /*
    ---------改---------
     */
    //-----处理修改状态操作
    @GetMapping("/exitRoom/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        //获取订单中的房间号
        String number = getRoomNumber(id);
        //根据订单中的房间号获取到 房间id
        Integer room_id = getRoomId(number);
        //获取客户id
        Integer customer_id = getCustomerId();
        /*
        进行状态更改
         */
        int result = roomOrderService.updateStatusAndCompleted(id,customer_id,room_id);
        /*
        对房间状态进行更改
         */
        int result2 = roomService.changeRoomStatus("空闲",false,number);
        if(result > 0 && result2 > 0){
            return "redirect:/to_roomIndex";
        }else{
            //届时 使用控制器让 其跳转到异常页面
            return "redirect:/error/notFound";
        }

    }

    //取消预订单
    @GetMapping("/cancelOrder/{id}")
    public String changeStatusAfterCancel(@PathVariable("id") Integer id){
        //获取订单中的房间号
        String number = getRoomNumber(id);
        //根据订单中的房间号获取到 房间id
        Integer room_id = getRoomId(number);
        //获取客户id
        Integer customer_id = getCustomerId();
        /*
        对订单状态进行状态更改
         */
        int result = roomOrderService.canceledOrder(id,customer_id,room_id);
        /*
        对房间状态进行更改
         */
        int result2 = roomService.changeRoomStatusAfterCancel("空闲",false,number);
        if(result > 0 && result2 > 0){
            return "redirect:/to_roomIndex";
        }else{
            //届时 使用控制器让 其跳转到异常页面
            return "redirect:/error/notFound";
        }
    }

    //付款
    @GetMapping("/payOrder/{id}")
    public String payOrderById(@PathVariable("id") Integer id){
        //暂时不模拟 订单付款
        int result = roomOrderService.payOrder(id);
        if(result > 0){
            return "redirect:/to_roomIndex";
        }else{
            return "redirect:/error/notFound";
        }
    }


    /*
    功能方法
     */
    //获取订单中的房间号
    public String getRoomNumber(Integer id){
        //根据传入的id获取到订单信息
        RoomOrder roomOrder = roomOrderService.getRoomOrderById(id);
        String number = roomOrder.getNumber();
        return number;
    }
    //根据房间号获取到房间id
    public Integer getRoomId(String number){
        //根据传入的number进行查询
        Room room = roomService.getRoomByNumber(number);
        Integer room_id = room.getId();
        return room_id;
    }
    //获取客户id
    public Integer getCustomerId(){
        //1.获取用户名
        String username = WebUtil.getUsername();
        //2.查询
        Customer customer = customerService.getCustomer(username);
        //3.获取到客户id
        Integer customer_id = customer.getId();
        return customer_id;
    }

}