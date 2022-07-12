package com.zzy.eban.service;

import com.zzy.eban.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.eban.pojo.Menu;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.pojo.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface IAdminService extends IService<Admin> {



    Admin getAdminByUserName(String name);

    RespBean login(String userName, String passWord, String code, HttpServletRequest request);

    /**
     * @description: 根据有户di查询用户角色
     * @author: zzy
     * @data: 2022/2/25 11:40
     */
    List<Role> getRoles(Integer adminId);

    List<Admin> getAllAdmins(String keywords);

    RespBean updateAdminRole(Integer adminId, Integer[] rids);
    /**
     * @description: 更新用户密码
     * @author: zzy
     * @data: 2022/3/27 17:08
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
/**
 * @description: 更新用户头像
 * @author: zzy
 * @data: 2022/5/2 15:38
 */
    RespBean updateUserFace(String url, Integer id, Authentication authentication);
}
