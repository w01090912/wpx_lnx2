package com.wpx.service;

import com.wpx.pojo.EmpSection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/4 17:57
 * @Version: v1.0.0
 */
public interface SectionService {


    List<EmpSection> selectAllEmpSection();

    String  selectBySectionID(String sectionName);

    int selectCount();


    void insEmpSection(EmpSection empSection);


    void delEmpSection(@Param("id") String id);
}
