package com.wpx.service.impl;

import com.wpx.mapper.EmployeesMapper;
import com.wpx.pojo.Employees;
import com.wpx.service.EmployeesService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/4 19:30
 * @Version: V.1.0.0
 */
@Service
@Transactional
public class EmployeesImpl implements EmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Employees> selectAllEmployees(Integer page, Integer rows) {
        return employeesMapper.selectAllEmployees(page,rows);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Employees> selectAllEmployees2() {
        return employeesMapper.selectAllEmployees2();
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public int selectCount() {
        return employeesMapper.selectCount();
    }

    @Override
    public List<Integer> selectSexCount(String sex) {
        return employeesMapper.selectSexCount(sex);
    }

    @Override
    public void insEmployees(Employees employees) {
        employeesMapper.insEmployees(employees);

    }

    @Override
    public void updateEmployees(Employees employees) {
        employeesMapper.updateEmployees(employees);
    }

    @Override
    public void delEmployees(Employees employees) {
        employeesMapper.updateEmployees(employees);
    }
}
