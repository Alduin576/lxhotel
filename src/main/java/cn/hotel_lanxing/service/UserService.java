package cn.hotel_lanxing.service;

import cn.hotel_lanxing.domain.Authority;
import cn.hotel_lanxing.domain.User;
import cn.hotel_lanxing.domain.UserWithAuthority;
import cn.hotel_lanxing.repository.AuthorityRepository;
import cn.hotel_lanxing.repository.UARepository;
import cn.hotel_lanxing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UARepository uaRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    //使用静态变量id

    //用户注册，添加至数据库
    /*
    第一步 向数据库插入用户信息
    第二部 向数据库插入权限信息
    第三步 向数据库插入用户-权限信息
     */
    public void addUser(String username,String password){
        //-------第一步开始------
        User user = new User();
        User userCheck = new User();
        //获取数据库所有的用户名
        List<User> users = userRepository.findAll();
        boolean flag = false;
        for(int i = 0;i<users.size();i++){
            if(!users.get(i).getUsername().equals(username)){
                flag=true;
                break;
            }
        }
        //如果用户名不重复
        if(flag){
            user.setUsername(username);
            //加密
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            //用户合法
            user.setValid(1);
            userRepository.save(user);
            //获取到自增的user id
            User userEntity = userRepository.saveAndFlush(user);
            int user_id = userEntity.getId();//可以成功获取到
            //--------第一步结束---------
            //-------第二步开始-------
            Authority authority = new Authority();
            authority.setAuthority("ROLE_customer");
            authorityRepository.save(authority);
            //获取到自增的权限 id
            //authorityEntity 是保存到数据库中的一条实体信息记录
            Authority authorityEntity = authorityRepository.saveAndFlush(authority);//其中authority是之前save的对象
            int authority_id = authorityEntity.getId();
            //------第二步结束-------
            //------第三步开始-------
            UserWithAuthority userWithAuthority = new UserWithAuthority(user_id,authority_id);
            uaRepository.save(userWithAuthority);
            //------第三步结束
        }else{
            System.out.println("用户名重复");
        }
    }

    //根据用户名查询用户 业务控制: 使用唯一用户名 查找
    public User getUser(String username){
        User user = null;
        Object o = redisTemplate.opsForValue().get("hotel_user_"+username);
        //如果缓存中有数据，则从缓存中查找数据
        if(o != null){
            user = (User) o;
        }else{
            user = userRepository.findUserByUsername(username);
            if(user != null){
                redisTemplate.opsForValue().set("hotel_user_",user);
            }
        }
        return user;
    }



    //业务控制: 根据用户名查找 相应的权限
    public List<Authority> getAuthorities(String username){
        List<Authority> authorities = null;
        Object o = redisTemplate.opsForValue().get("hotel_authority_"+username);
        if(o != null){
            authorities = (List<Authority>) o;
        }else{
            authorities = authorityRepository.findAuthoritiesByUsername(username);
            if(authorities != null){
                redisTemplate.opsForValue().set("hotel_authorities_"+username,authorities);
            }
        }
        return authorities;
    }

    /*
    修改
     */
    //修改用户信息 用户名 密码
    public int updateUser(String username,String password,String c_username){
        //先将传递的密码进行加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //secretedPassword表示加密密码
        String secretedPassword = encoder.encode(password);

        //进行修改
        int result = userRepository.updateUserByName(username,secretedPassword,c_username);
        if(result>0){
            return 1;
        }else{
            return 0;
        }
    }
}
