package com.wpx.mapper;

import com.wpx.pojo.Employees;
import com.wpx.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/4 12:57
 * @Version: v.1.0.0
 */
public interface EmployeesMapper {


    List<Employees> selectAllEmployees(@Param("page") Integer page, @Param("rows") Integer rows);
    List<Employees> selectAllEmployees2();

    /**
     * @Author: wpx
     * @Description: 用户信息总条数
     * @Date: 2020/3/3
     */
    int selectCount();

    List<Integer> selectSexCount(@Param("sex") String sex);


    /**
     * @Author: wpx
     * @Description: 增加用户
     * @Date: 2020/3/2
     * @param employees
     */
    void insEmployees(Employees employees);

    /**
     *
     * @Author: wpx
     * @Description: 修改用户信息
     * @Date: 2020/3/2
     * @param employees
     */
    void updateEmployees(Employees employees);

}
