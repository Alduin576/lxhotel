package cn.hotel_lanxing.repository;

import cn.hotel_lanxing.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 房间 数据访问层
 */
public interface RoomRepository extends JpaRepository<Room,Integer> {

    /*
    ---------查-----------
     */
    //根据房间号查询房间
    public Room findRoomByNumber(String number);
    /*
    根据id查询单个房间
     */
    public Room findRoomById(Integer id);

    /*
    查询所有的房间
     */
    public List<Room> findAll();

    /*
    ---------改
     */
    //根据房间号更改房间的状态  完成退房功能
    @Modifying
    @Transactional
    @Query(value="update t_room set status=?1,is_occupied=?2 where number=?3",nativeQuery=true)
    public int updateRoomStatusAndIsOccupied(String status,boolean isOccupied,String number);

    //根据房间号更改房间的状态  完成取消预订功能
    @Modifying
    @Transactional
    @Query(value="update t_room set status=?1,is_occupied=?2 where number=?3",nativeQuery=true)
    public int updateRoomStatusAndIsOccupiedOfCancel(String status,boolean isOccupied,String number);

}
