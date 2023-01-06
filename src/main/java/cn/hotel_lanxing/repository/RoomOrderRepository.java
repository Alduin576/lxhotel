package cn.hotel_lanxing.repository;

import cn.hotel_lanxing.domain.RoomOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *  订房功能 订单功能模块 数据访问层 DAO
 */
public interface RoomOrderRepository extends JpaRepository<RoomOrder,Integer> {

    /*
    严格按 查 改  删  的顺序排列
     */
    /*
    ----------查---------
     */
    //------查询所有的订单
    public List<RoomOrder> findAll();

    //-----根据id查找单个的订单信息
    public RoomOrder findRoomOrderById(Integer id);

    //------根据客户姓名找到该用户的所有订单
    public List<RoomOrder> findRoomOrdersByName(String name);


    /*
    ----------改
     */
    //根据房间订单id来更改房间订单是否完成  退房功能
    @Modifying
    @Transactional
    @Query(value="update t_room_order set is_completed=?1 where id=?2",nativeQuery=true)
    public int updateCompletedById(boolean isCompleted,Integer id);

    //根据房间订单id来更改房间预定订单是否取消，   取消订单功能
    @Modifying
    @Transactional
    @Query(value="update t_room_order set is_canceled=?1 where id=?2",nativeQuery=true)
    public int updateCanceledById(boolean isCanceled,Integer id);

    //根据房间订单id 来更改房间预订单是否变为激活   付款功能
    @Modifying
    @Transactional
    @Query(value="update t_room_order set status=?1 where id=?2",nativeQuery=true)
    public int updateOrderStatusById(boolean status,Integer id);








}
