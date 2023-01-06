package cn.hotel_lanxing.repository;


import cn.hotel_lanxing.domain.UserWithAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户-权限访问层
 */
public interface UARepository extends JpaRepository<UserWithAuthority,Integer> {

}
