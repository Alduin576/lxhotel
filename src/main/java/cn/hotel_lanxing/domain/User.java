package cn.hotel_lanxing.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 存放用户信息的类
 * 用户类型有多种： 管理员，前台服务人员，保洁人员（暂未实现），客户
 */
@Entity(name = "t_user")
public class User {
    //    定义静态变量 存放用户信息的用户名
    public static String USERNAME = null;
    //定义成员变量
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//用户id

    private String username;//用户名
    private String password;//密码
    private int valid;//用户是否合法码
//    private String type;//类型

//    private String email;//用户邮箱

    //空构造方法
    public User(){}

    public User(Integer id, String username, String password, int valid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.valid = valid;
//        this.email = email;
    }

    /*
    setter getter方法
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", valid=" + valid +
                '}';
    }

}
