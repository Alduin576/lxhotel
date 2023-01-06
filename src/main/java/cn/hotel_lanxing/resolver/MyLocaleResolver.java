package cn.hotel_lanxing.resolver;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
//接收语言的参数 - 传进来的为 zh_CN这样的参数
        String language = request.getParameter("language");
        //使用默认的语言 - 在文中就是login.properties文件里配置
        Locale locale = Locale.getDefault();
        //判断接收的参数是否为空，不为空就设置为该语言
        if(!StringUtils.isEmpty(language)){
            //将参数分隔 - 假设传进来的为 ‘zh_CN'
            String[] split = language.split("_");
            //语言编码：zh 地区编码 CN
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
