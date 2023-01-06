package cn.hotel_lanxing.repository;

import cn.hotel_lanxing.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

//    根据username查询 权限
    @Query(value="select a.id,a.authority from t_authority a inner join t_user_authority ua on ua.authority_id = a.id inner join t_user u on ua.user_id = u.id where u.username=?1",nativeQuery=true)
    public List<Authority> findAuthoritiesByUsername(String username);



}
