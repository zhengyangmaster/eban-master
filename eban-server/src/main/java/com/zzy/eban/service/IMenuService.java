package com.zzy.eban.service;

import com.sun.org.apache.xerces.internal.impl.XMLEntityHandler;
import com.zzy.eban.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.eban.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenusByAdminId();

    /**
     * @description: 根据角色获取菜单列表
     * @author: zzy
     * @data: 2022/2/24 14:18
     * @return
     */
    List<Menu> getMenuByRole();

    /**
     * @description: 查询所有菜单
     * @author: zzy
     * @data: 2022/3/19 22:39
     */
    List<Menu> getAllMenus();
}
