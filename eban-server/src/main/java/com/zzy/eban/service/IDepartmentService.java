package com.zzy.eban.service;

import com.zzy.eban.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.eban.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllDepartment();

    RespBean addDep(Department department);

    RespBean deleteDep(Integer id);
}
