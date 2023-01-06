package cn.hotel_lanxing.service;

import cn.hotel_lanxing.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceDetailImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //通过其业务方法获取到user和authorities
        cn.hotel_lanxing.domain.User user = userService.getUser(s);
        List<Authority> authorities = userService.getAuthorities(s);

        //对用户及用户权限进行封装
        List<SimpleGrantedAuthority> list =authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
        //返回封装的userDetails用户详情类
        if(user != null){
            UserDetails userDetails = new User(user.getUsername(), user.getPassword(), list);
            return userDetails;
        }else{
            //如果查询的用户 不存在（用户名不存在），必须抛出异常
            throw new UsernameNotFoundException("当前用户不存在");
        }
    }
}
