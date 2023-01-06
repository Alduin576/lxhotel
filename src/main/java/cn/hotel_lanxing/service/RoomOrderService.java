package cn.hotel_lanxing.service;

import cn.hotel_lanxing.domain.*;
import cn.hotel_lanxing.repository.RCRepository;
import cn.hotel_lanxing.repository.RoomOrderRepository;
import cn.hotel_lanxing.repository.RoomRepository;
import cn.hotel_lanxing.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 *  订房功能 订单功能模块 业务层
 */
public class RoomOrderService {
    @Autowired
    private RoomOrderRepository roomOrderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RCRepository rcRepository;
    @Autowired
    private RoomRepository roomRepository;

    /*
    -------查-----
     */
    //------查询所有的房间订单 （后台）
    public List<RoomOrder> getAllRoomOrders(){
        return roomOrderRepository.findAll();
    }
    //------根据id查询单个的房间订单信息
    public RoomOrder getRoomOrderById(Integer id){
        return roomOrderRepository.findRoomOrderById(id);
    }
    //------根据客户姓名查询单个的房间订单信息
    public List<RoomOrder> getRoomOrderByName(String name){
        return roomOrderRepository.findRoomOrdersByName(name);
    }



    /*
    -------增
     */
    public boolean addRoomOrder(RoomOrder roomOrderForm){
        //创建房间订单实例
        RoomOrder roomOrder = getDataFromForm(roomOrderForm);
        //如果客户是预订,则将status的值设为0 表示未激活
        if(roomOrder.getType().equals("BO")){
            roomOrder.setStatus(false);
        }
        //如果客户是直接下单，则将status的值设为true 也就是1，表示激活
        if(roomOrder.getType().equals("PO")){
            roomOrder.setStatus(true);
        }
        //使用roomOrderRepository.save()将数据持久化
        roomOrderRepository.save(roomOrder);
        //获取房间的id
        String number = roomOrder.getNumber();
        //根据房间号获取到id
        Room room = roomRepository.findRoomByNumber(number);
        Integer room_id = room.getId();
        //向后台的 订单历史记录中插入数据
        //获取客户的id
        Customer customer = customerService.getCustomer(WebUtil.getUsername());
        int customer_id = customer.getId();
        RoomWithCustomer roomWithCustomer = new RoomWithCustomer();
        roomWithCustomer.setCustomer_id(customer_id);
        roomWithCustomer.setRoom_id(room_id);
        roomWithCustomer.setCompleted(false);
        //持久化
        rcRepository.save(roomWithCustomer);
        /*
        将要订的房间的状态改为 繁忙 是否占用改为1
         */
        //获取房间号
        String status = "繁忙";
        boolean isOccupied = true;
        int result = roomRepository.updateRoomStatusAndIsOccupied(status,isOccupied,number);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    /*
    -------改
     */
    //修改房间订单状态中的 isCompleted，及修改 t_customer_room表中 is_completed
    //第一个参数为RoomOrderRepository中的参数 订单id，第二参数为客户-房间关联表中的客户id，第三个参数为客户-房间关联表中的房间id
    public int updateStatusAndCompleted(Integer id,Integer customer_id,Integer room_id){
        //1.修改订单中的是否完成
        boolean isCompleted = true;
        int result = roomOrderRepository.updateCompletedById(isCompleted,id);
        //2.修改房间-客户关联表中的 是否完成
        boolean isCompleted2 = true;
        int result2 = rcRepository.updateCompletedById(isCompleted2,customer_id,room_id);
        int totalResult = result + result2;
        if(totalResult > 0){
            return totalResult;
        }else{
            //表示失败
            return 0;
        }
    }
    //取消订单功能实现代码
    public int canceledOrder(Integer id,Integer customer_id,Integer room_id){
        //1.修改订单中的是否取消 取消
        int result = roomOrderRepository.updateCanceledById(true,id);
        //2.修改房间与客户关联表中的是否取消  取消
        int result2= rcRepository.updateCanceledById(true,customer_id,room_id);
        int totalResult = result+result2;
        if(totalResult >0){
            return totalResult;
        }else{
            return 0;//表示失败
        }
    }
    //付款功能实现代码
    public int payOrder(Integer id){
        //修改订单中的状态为激活
        int result = roomOrderRepository.updateOrderStatusById(true,id);
        if(result > 0){
            return result;
        }else{
            return 0;//表示失败
        }
    }


    /*
    定义 功能方法区
     */
    //定义一个方法用来获取表单中的数据,并向实例填数据
    public RoomOrder getDataFromForm(RoomOrder roomOrderForm){
        RoomOrder roomOrder = new RoomOrder();
        //获取表单信息
        String number = roomOrderForm.getNumber();
        String price = roomOrderForm.getPrice();
        String name = roomOrderForm.getName();
        String telephone = roomOrderForm.getTelephone();
        String idCode = roomOrderForm.getIdCode();
        String type = roomOrderForm.getType();
        //填数据
        roomOrder.setNumber(number);
        roomOrder.setPrice(price);
        roomOrder.setName(name);
        roomOrder.setTelephone(telephone);
        roomOrder.setType(type); //设置订单类型
        roomOrder.setIdCode(idCode);
        roomOrder.setCompleted(false); //是否完成订单，刚创建的订单默认为false
        roomOrder.setCanceled(false); //是否取消的订单，刚创建的订单为false
        return roomOrder;
    }
}
