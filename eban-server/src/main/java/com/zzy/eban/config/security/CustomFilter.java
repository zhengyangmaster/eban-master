package com.zzy.eban.config.security;

import com.zzy.eban.mapper.MenuMapper;
import com.zzy.eban.pojo.Menu;
import com.zzy.eban.pojo.Role;
import com.zzy.eban.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.jws.Oneway;
import java.util.Collection;
import java.util.List;

/**
 * @author ZhuZhengYang
 * @description 权限控制
 * 根据角色查找菜单列表
 * @since 2022/2/25
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation) o).getFullRequestUrl();
        List<Menu> menus = menuService.getMenuByRole();
        for (Menu menu : menus) {
            //判断请求url与用户角色是否匹配
            if (antPathMatcher.match(menu.getUrl(),requestUrl)){

                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(str);
            }

        }
        //没有匹配的url默认登录即可
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
