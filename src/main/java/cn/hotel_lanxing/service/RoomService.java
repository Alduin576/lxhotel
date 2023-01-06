package cn.hotel_lanxing.service;

import cn.hotel_lanxing.domain.Room;
import cn.hotel_lanxing.repository.RCRepository;
import cn.hotel_lanxing.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 房间功能的 业务层
 */
@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RCRepository rcRepository;

    /*
    通过id查询相应的房间信息
     */
    public Room getRoom(Integer id){
        return roomRepository.findRoomById(id);
    }
    /*
    根据房间号查询房间信息
     */
    public Room getRoomByNumber(String number){
        return roomRepository.findRoomByNumber(number);
    }
    /*
    获取所有的房间，包括繁忙和空闲
     */
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }



    /*
    --------改--------
     */
    //更改房间的状态为 空闲，未占用  完成退房功能之后的使房间可用
    //传入的参数为 number 房间号
    public int changeRoomStatus(String status,boolean isOccupied,String number){
        int result = roomRepository.updateRoomStatusAndIsOccupied(status,isOccupied,number);
        if(result > 0){
            return result;
        }else{
            return 0; //表示失败
        }
    }

    //更改房间为 空闲，未占用  完成取消预订单之后的使房间可用
    //传入的参数为 number
    public int changeRoomStatusAfterCancel(String status,boolean isOccupied,String number){
        int result = roomRepository.updateRoomStatusAndIsOccupiedOfCancel(status,isOccupied,number);
        if(result > 0){
            return result;
        }else{
            return 0;//表示失败
        }
    }

}
