package com.wpx.service.impl;

import com.wpx.mapper.SectionMapper;
import com.wpx.pojo.EmpSection;
import com.wpx.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/4 18:00
 * @Version: V_1.0.0
 */
@Service
@Transactional
public class SectionImpl implements SectionService {


    @Autowired
    private SectionMapper sectionMapper;
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<EmpSection> selectAllEmpSection() {
        return sectionMapper.selectAllEmpSection();
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public String selectBySectionID(String sectionName) {
        return sectionMapper.selectBySectionID(sectionName);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public int selectCount() {
        return sectionMapper.selectCount();
    }

    @Override
    public void insEmpSection(EmpSection empSection) {
        sectionMapper.insEmpSection(empSection);
        System.out.println("添加成功");

    }

    @Override
    public void delEmpSection(String id) {
        sectionMapper.delEmpSection(id);
        System.out.println("删除成功");

    }
}
