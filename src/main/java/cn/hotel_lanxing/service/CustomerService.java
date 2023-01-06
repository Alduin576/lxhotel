package cn.hotel_lanxing.service;

import cn.hotel_lanxing.domain.Customer;
import cn.hotel_lanxing.domain.CustomerWithUser;
import cn.hotel_lanxing.domain.User;
import cn.hotel_lanxing.repository.CURepository;
import cn.hotel_lanxing.repository.CustomerRepository;
import cn.hotel_lanxing.repository.UserRepository;
import cn.hotel_lanxing.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CURepository cuRepository;

//    查询客户
    public Customer getCustomer(String username){
        return customerRepository.findCustomerByName(username);
    }

    /*
    插入客户信息
     */
    public void fillCustomerInfo(Customer customer){
        //------first--------
        customerRepository.save(customer);
        //gain the auto increment id
        Customer customerEntity = customerRepository.saveAndFlush(customer);
        int customer_id = customerEntity.getId();

        //------second--------
        //gain the user info(id) to bind with customer
        String username = User.USERNAME;
        User user = userRepository.findUserByUsername(username);
        int user_id = user.getId();

        //-------third--------
        //finish bind
        CustomerWithUser customerWithUser = new CustomerWithUser(user_id,customer_id);
        cuRepository.save(customerWithUser);
    }

    /*
    修改
     */
    //修改客户信息
    public int updateCustomer(String telephone,String email,int id){
        int result = customerRepository.updateCustomerById(telephone,email,id);
        if(result>0){
            return 1;
        }else{
            return 0;
        }
    }

    //获取当前客户的姓名
    public String getCustomerName(){
        Customer customer = getCustomer(WebUtil.getUsername());
        if(customer!=null){
            return customer.getName();
        }
        return "";
    }
}
