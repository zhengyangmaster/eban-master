package com.zzy.eban.mapper;

import com.zzy.eban.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.eban.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
