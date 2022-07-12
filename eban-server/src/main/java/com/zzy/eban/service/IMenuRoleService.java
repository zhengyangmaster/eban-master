package com.zzy.eban.service;

import com.zzy.eban.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.eban.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * @description: 更新菜单列表
     * @author: zzy
     * @data: 2022/3/20 11:26
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
