package cn.hotel_lanxing.repository;


import cn.hotel_lanxing.domain.CustomerWithUser;
import cn.hotel_lanxing.domain.UserWithAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 客户关联用户的 数据访问层
 */
public interface CURepository extends JpaRepository<CustomerWithUser,Integer> {



}
