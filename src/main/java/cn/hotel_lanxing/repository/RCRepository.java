package cn.hotel_lanxing.repository;

import cn.hotel_lanxing.domain.Customer;
import cn.hotel_lanxing.domain.Room;
import cn.hotel_lanxing.domain.RoomOrder;
import cn.hotel_lanxing.domain.RoomWithCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 房间与客户关联的数据访问层
 */
public interface RCRepository extends JpaRepository<RoomWithCustomer,Integer> {

    /*
    按照查 改 删的顺序排列
     */
    /*---------查---------*/
    //查询所有的订单历史记录





    /*-------改----------*/
    //根据客户id和房间id 来更改 订单是否完成  实现退房功能
    @Modifying
    @Transactional
    @Query(value="update t_room_customer set is_completed=?1 where customer_id=?2 and room_id=?3",nativeQuery=true)
    public int updateCompletedById(boolean completed,Integer customer_id,Integer room_id);

    //根据客户id和房间id 来更改 订单是否取消 完成取消订单功能
    @Modifying
    @Transactional
    @Query(value="update t_room_customer set is_canceled=?1 where customer_id=?2 and room_id=?3",nativeQuery=true)
    public int updateCanceledById(boolean canceled,Integer customer_id,Integer room_id);

}
