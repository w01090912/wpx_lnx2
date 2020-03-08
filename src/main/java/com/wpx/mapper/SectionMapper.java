package com.wpx.mapper;

import com.wpx.pojo.EmpSection;
import com.wpx.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/4 12:58
 * @Version: v.1.0.0
 */
public interface SectionMapper {
    List<EmpSection> selectAllEmpSection();

    /**
     * @Author: wpx
     * @Description: 用户信息总条数
     * @Date: 2020/3/3
     * @param
     */
    int selectCount();

    /**
     * @Author: wpx
     * @Description: 增加部门
     * @Date: 2020/3/2
     * @param empSection
     */
    void insEmpSection(EmpSection empSection);

    /**
     *
     * @Author: wpx
     * @Description: 删除部门
     * @Date: 2020/3/2
     * @param
     * @param id
     */
    void delEmpSection(@Param("id") String id);

    String selectBySectionID(@Param("sectionName") String sectionName);
}
