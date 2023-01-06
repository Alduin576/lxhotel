package cn.hotel_lanxing.domain;

import javax.persistence.*;

@Entity(name = "t_room_customer")
public class RoomWithCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //room_id 相当于rOrderId;
    private Integer room_id;
    private Integer customer_id;
    @Column(name = "is_completed")
    private boolean isCompleted; //订单是否完成
    @Column(name = "is_canceled")
    private boolean isCanceled;// 订单是否被取消



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    @Override
    public String toString() {
        return "RoomWithCustomer{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", customer_id=" + customer_id +
                ", isCompleted=" + isCompleted +
                ", isCanceled=" + isCanceled +
                '}';
    }
}
