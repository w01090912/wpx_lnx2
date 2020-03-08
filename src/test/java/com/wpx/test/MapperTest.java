package com.wpx.test;

import com.wpx.WpxLnx2Application;
import com.wpx.mapper.EmployeesMapper;
import com.wpx.mapper.SectionMapper;

import com.wpx.pojo.EmpSection;
import com.wpx.pojo.Employees;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/4 15:09
 * @Version: V_1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpxLnx2Application.class)
public class MapperTest {


    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private EmployeesMapper employeesMapper;

    @Test
    public void testSection(){
        int count = sectionMapper.selectCount();
        System.out.println(count);
        List<EmpSection> empSections = sectionMapper.selectAllEmpSection();
        System.out.println(empSections);


    }
    @Test
    public void testEmp(){

        List<Employees> emp = employeesMapper.selectAllEmployees(1,3);
        for (Employees employees:emp) {
            System.out.println(employees);
        }



    }

}
