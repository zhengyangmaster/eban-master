package com.zzy.eban.controller;


import com.aliyun.oss.OSSClient;
import com.zzy.eban.config.OssConfig;
import com.zzy.eban.pojo.Admin;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.pojo.Role;
import com.zzy.eban.service.IAdminService;
import com.zzy.eban.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private OSSClient ossClient;

    @ApiOperation(value = "获取所有操作人员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords) {

        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");

    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("更新操作员成功！");
        }
        return RespBean.error("更新操作员失败");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    private List<Role> getAllRole() {
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }

    @ApiOperation(value = "更新操作员头像")
    @PostMapping("/admin/userface")
    public RespBean updateUserFace(MultipartFile file, Integer id, Authentication authentication) {

        if (file != null) {
            String bucketName = "qingchengzzycom";
            String fileName = "faceimage/" + UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
            try {
                ossClient.putObject(bucketName, fileName, file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = "http://" + bucketName + "." + ossClient.getEndpoint().toString().replace("http://", "") + "/" + fileName;

            return adminService.updateUserFace(url, id, authentication);
        }
        return RespBean.error("请上传头像文件");
    }


}
