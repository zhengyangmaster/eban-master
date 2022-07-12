package com.zzy.eban.mapper;

import com.zzy.eban.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.eban.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByAdminIds(Integer id);

    List<Menu> getMenuByRole();


    List<Menu> getAllMenus();
}
