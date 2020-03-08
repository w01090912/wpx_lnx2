package com.wpx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/4 12:52
 * @Version: V_1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpSection implements Serializable {

    private String section_id;
    private String section_name;
    private List<Employees> employeess;


}
