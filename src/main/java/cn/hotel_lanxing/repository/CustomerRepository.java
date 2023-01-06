package cn.hotel_lanxing.repository;

import cn.hotel_lanxing.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户 数据访问层
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    /*
    查询所有客户
     */
    public List<Customer> findAll();

    /*
    根据用户id来查询客户的信息
     */
    public Customer findCustomerById(Integer id);

    /*
    根据用户名查询客户信息
    多表查询
     */
    @Query(value="select c.id,c.id_code,c.age,c.email,c.name,c.origin,c.sex,c.telephone,c.url from t_customer c inner join t_user_customer uc on c.id=uc.customer_id inner join t_user u on u.id=uc.user_id where u.username = ?1",nativeQuery=true)
    public Customer findCustomerByName(String username);

    /*
    根据用户名查询到的客户信息中的id来修改 客户信息
     */
    @Modifying
    @Transactional
    @Query(value="update t_customer set telephone=?1,email=?2 where id=?3",nativeQuery=true)
    public int updateCustomerById(String telephone,String email,int id);


}
