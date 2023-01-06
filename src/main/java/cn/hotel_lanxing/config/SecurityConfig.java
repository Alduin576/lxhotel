package cn.hotel_lanxing.config;


import cn.hotel_lanxing.service.UserServiceDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserServiceDetailImpl userServiceDetail;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        密码得设置密码编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        让admin 通过内存用户认证
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN");
//        使用userDetailsService进行身份认证
        auth.userDetailsService(userServiceDetail).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义用户访问控制
        http.authorizeRequests()
                //运算顺序从左到右
                .antMatchers("/").permitAll()
                //放开注册功能的权限
                .antMatchers("/reg/toReg","/reg/doReg").permitAll()
                //放开用户填写客户信息的权限
                .antMatchers("customer/doFill").permitAll()
                .antMatchers("admin/**").hasRole("admin")
                //下面这段被注释的内容不可再加上去
                //.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/myLogout").logoutSuccessUrl("/startLogin").permitAll()
                .and()
                .formLogin().loginPage("/startLogin").permitAll()
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/startLogin?error");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //不拦截的静态资源
        web.ignoring().antMatchers("/css/**","/image/**","/js/**","/logReg/**");
    }
}
