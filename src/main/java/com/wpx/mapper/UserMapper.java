package com.wpx.mapper;

import com.wpx.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/2 17:37
 * @Version: v1.0.0
 */
public interface UserMapper {


    List<User> selectAllUser(@Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * @Author: wpx
     * @Description: 用户信息总条数
     * @Date: 2020/3/3 
     * @param 
     */
    int selectCount();

    /**
     * @Author: wpx
     * @Description: 查询用户
     * @Date: 2020/3/2
     * @param username
     */
    User selectUser(@Param("username") String username, @Param("password") String password);
    /**
     * @Author: wpx
     * @Description: 增加用户
     * @Date: 2020/3/2
     * @param user
     */
    void insUser(User user);

    /**
     *
     * @Author: wpx
     * @Description: 修改用户信息
     * @Date: 2020/3/2
     * @param user
     */
    void updateUser(User user);

    /**
     *
     * @Author: wpx
     * @Description: 删除用户
     * @Date: 2020/3/2
     * @param
     * @param id
     */
    void delUser(String id);
}
