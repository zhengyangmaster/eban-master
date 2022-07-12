package com.zzy.eban.controller;

import com.zzy.eban.pojo.Admin;
import com.zzy.eban.pojo.AdminLoginParam;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/2/22
 */
@RestController
@Api(tags = "loginController")
public class LoginController {

    @Autowired
    IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam param, HttpServletRequest request) {
        return adminService.login(param.getUserName(), param.getPassWord(),param.getCode(), request);

    }

    @ApiOperation(value = "获取当前登录信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String name = principal.getName();
        Admin admin = adminService.getAdminByUserName(name);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;


    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功,欢迎下次再来");
    }
}
