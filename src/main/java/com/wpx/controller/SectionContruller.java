package com.wpx.controller;

import com.wpx.ACommonAPI.BaseNorms;
import com.wpx.pojo.EmpSection;
import com.wpx.pojo.User;
import com.wpx.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: wpx
 * @Date: 2020/3/4 17:55
 * @Version: V_1.0.0
 */
@Controller
@RequestMapping("/section")
public class SectionContruller {

    @Autowired
    private SectionService sectionService;

    @RequestMapping("/show")
    @ResponseBody
    public List<EmpSection> show(Integer page,Integer rows){
        System.out.println("展示数据的action");
        List<EmpSection> data = sectionService.selectAllEmpSection();// page页下的rows条数据
        return data;
    }




}
