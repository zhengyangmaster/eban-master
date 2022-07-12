package com.zzy.eban.mapper;

import com.zzy.eban.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.eban.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface AdminMapper extends BaseMapper<Admin> {

    //List<Menu> getMenusByAdminIds(Integer id);

    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
