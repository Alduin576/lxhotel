package cn.hotel_lanxing.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_user_authority")
public class UserWithAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;//这里是为了使 其与参数同名，故用下划线命名法
    private Integer authority_id;

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

    public Integer getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(Integer authority_id) {
        this.authority_id = authority_id;
    }

    //只有两个参数的构造方法
    public UserWithAuthority(Integer user_id,Integer authority_id){
        this.user_id=user_id;
        this.authority_id=authority_id;
    }

    public UserWithAuthority(Integer id, Integer user_id, Integer authority_id) {
        this.id = id;
        this.user_id = user_id;
        this.authority_id = authority_id;
    }

    public UserWithAuthority(){}

    @Override
    public String toString() {
        return "UserWithAuthority{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", authority_id=" + authority_id +
                '}';
    }
}
