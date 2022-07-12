package com.zzy.eban.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzy.eban.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * @description: 获取所有员工分页
     * @author: zzy
     * @data: 2022/3/22 16:50
     */

    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee,
                                      @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> getEmployee(Integer id);

    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}
