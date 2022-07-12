package com.zzy.eban.service.impl;

import com.zzy.eban.pojo.Department;
import com.zzy.eban.mapper.DepartmentMapper;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * @description: 获取所有部门
     * @author: zzy
     * @data: 2022/3/20 16:13
     */
    @Override
    public List<Department> getAllDepartment() {

       return departmentMapper.getAllDepartment(-1);

    }
/**
 * @description: 添加部门
 * @author: zzy
 * @data: 2022/3/20 17:56
 */
    @Override
    public RespBean addDep(Department department) {

    department.setEnabled(true);

    departmentMapper.addDep(department);

    department.getResult();

    if (1==department.getResult()){
        return RespBean.success("添加部门成功",department);
    }
        return RespBean.error("添加部门失败");
    }

    /**
     * @description: 删除部门
     * @author: zzy
     * @data: 2022/3/20 17:56
     */
    @Override
    public RespBean deleteDep(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDep(department);
        if (-2==department.getResult()){
            return RespBean.error("该部门下还有子部门，无法删除");
        }
        if (-1==department.getResult()){
            return RespBean.error("该部门下还有员工，无法删除");
        }
        if (1==department.getResult()){
            return RespBean.success("删除成功！");

        }
        return RespBean.error("删除失败");
    }
}
