package com.zzy.eban.controller;


import com.zzy.eban.pojo.Menu;
import com.zzy.eban.service.IAdminService;
import com.zzy.eban.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过用户id查询用户列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){

       return menuService.getMenusByAdminId();

    }


}
