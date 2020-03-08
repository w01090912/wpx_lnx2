package com.wpx.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wpx
 * @Date: 2020/3/2 17:30
 * @Version: V_1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String username;
    private String password;
    private String realname;
    private String age;
    private String sex;

}
