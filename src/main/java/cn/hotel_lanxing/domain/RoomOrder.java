package cn.hotel_lanxing.domain;

import javax.persistence.*;

@Entity(name = "t_room_order")
public class RoomOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 房间订单id
    private String number;//房间号
    private String price;//房价
    private String name;//客户姓名
    private String telephone;//电话号码
    @Column(name = "id_code")
    private String idCode; //身份证号码
    private String type;// 订单类型  预订  直接下单
    private boolean status;//订单状态  激活 待激活
    private boolean isCanceled;//是否是退单 取消
    private boolean isCompleted;//是否完成订单

    public RoomOrder(){}

    public RoomOrder(String number, String price, String name, String telephone, String idCode, String type, boolean status) {
        this.number = number;
        this.price = price;
        this.name = name;
        this.telephone = telephone;
        this.idCode = idCode;
        this.type = type;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "RoomOrder{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", idCode='" + idCode + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", isCanceled=" + isCanceled +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
