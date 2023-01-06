package cn.hotel_lanxing.repository;


import cn.hotel_lanxing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {

    //根据用户名查询用户
    public User findUserByUsername(String username);

    //根据id查询用户
    public User findUserById(Integer id);

    @Modifying
    @Transactional
    /*
    使用update和delete需要加上 @Modifying注解和@Transactional注解
     */
    //根据用户名来更新用户信息 用户名 密码
    @Query(value="update t_user set username=?1,password=?2 where username=?3",nativeQuery=true)
    public int updateUserByName(String username,String password,String c_username);




}
