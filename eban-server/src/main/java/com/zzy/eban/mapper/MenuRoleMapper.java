package com.zzy.eban.mapper;

import com.zzy.eban.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
/**
 * @description: 更新角色菜单
 * @author: zzy
 * @data: 2022/3/20 13:51
 */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
