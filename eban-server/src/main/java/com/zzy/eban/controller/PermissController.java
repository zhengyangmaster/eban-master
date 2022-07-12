package com.zzy.eban.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.zzy.eban.pojo.Menu;
import com.zzy.eban.pojo.MenuRole;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.pojo.Role;
import com.zzy.eban.service.IMenuRoleService;
import com.zzy.eban.service.IMenuService;
import com.zzy.eban.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限组管理
 *
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/3/19
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "查看所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }


    @ApiOperation(value = "添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加角色成功");

        }
        return RespBean.error("添加角色失败");
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return RespBean.success("更新角色成功");
        }
        return RespBean.error("更新角色失败");

    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("删除角色成功");
        }
        return RespBean.error("删除角色失败");

    }


    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenu() {
        return menuService.getAllMenus();


    }

    @ApiOperation(value = "根据角色ID查找菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
       return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid)).stream()
               .map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation(value = "更新角色菜单列表")
    @PutMapping("/updateMenuRole")
    public RespBean updateMenuRole(Integer rid,Integer [] mids){

        return menuRoleService.updateMenuRole(rid,mids);
    }
}

