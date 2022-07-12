package com.zzy.eban.service.impl;

import com.zzy.eban.pojo.Admin;
import com.zzy.eban.pojo.Menu;
import com.zzy.eban.mapper.MenuMapper;
import com.zzy.eban.pojo.Role;
import com.zzy.eban.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @description: 根据用户id获取菜单列表
     * @author: zzy
     * @data: 2022/2/23 19:30
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getId();
        //从redis中获取菜单列表
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = ((List<Menu>) valueOperations.get("menu_" + id));
        //如果redis中不存在则去数据库中进行查询
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByAdminIds(id);
            //将数据设置到redis中
            valueOperations.set("menu_" + id, menus);

        }

        return menus;
    }

    @Override
    public List<Menu> getMenuByRole() {
        return menuMapper.getMenuByRole();

    }

    /**
     * @description: 查询所有菜单
     * @author: zzy
     * @data: 2022/3/19 22:39
     */
    @Override
    public List<Menu> getAllMenus() {

        return menuMapper.getAllMenus();

    }
}
