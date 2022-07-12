package com.zzy.eban.mapper;

import com.zzy.eban.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartment(Integer parentId);
/**
 * @description: 添加部门
 * @author: zzy
 * @data: 2022/3/20 16:40
 */
    void addDep(Department dep);

    void deleteDep(Department department);
}
