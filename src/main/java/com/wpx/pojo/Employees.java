package com.wpx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wpx
 * @Date: 2020/3/4 12:41
 * @Version: V_1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees {
    private String id;
    private String empName;
    private String empAge;
    private String empSex;
    private String empPost;
    private String empImage;
    private String empSection;
    private String status;
    private EmpSection section;





}
