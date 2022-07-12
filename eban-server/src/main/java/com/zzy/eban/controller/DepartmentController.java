package com.zzy.eban.controller;


import com.zzy.eban.pojo.Department;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import javassist.bytecode.DeprecatedAttribute;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResizableByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;


    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }


    @ApiOperation(value = "添加部门信息")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department dep){

     return   departmentService.addDep(dep);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id){
        return departmentService.deleteDep(id);
    }

}
