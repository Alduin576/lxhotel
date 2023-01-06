package cn.hotel_lanxing.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_user_customer")
public class CustomerWithUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer customer_id;

    public CustomerWithUser(){}

    public CustomerWithUser(Integer user_id, Integer customer_id) {
        this.user_id = user_id;
        this.customer_id = customer_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "CustomerWithUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", customer_id=" + customer_id +
                '}';
    }
}
