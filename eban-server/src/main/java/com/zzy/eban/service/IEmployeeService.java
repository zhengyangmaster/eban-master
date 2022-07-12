package com.zzy.eban.service;

import com.zzy.eban.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.pojo.RespPageBean;
import org.apache.catalina.LifecycleState;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * @description:获取所有员工分页
     * @author: zzy
     * @data: 2022/3/22 16:42
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * @description: 获取最大工号
     * @author: zzy
     * @data: 2022/3/22 19:43
     */
    RespBean maxWorkId();

    /**
     * @description: 添加员工
     * @author: zzy
     * @data: 2022/3/22 19:55
     */
    RespBean addEmp(Employee employee);

    List<Employee> getEmployee(Integer id);

    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
