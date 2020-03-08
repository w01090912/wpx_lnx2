package com.wpx.controller;

import com.wpx.ACommonAPI.BaseNorms;
import com.wpx.pojo.User;
import com.wpx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: wpx
 * @Date: 2020/3/3 23:21
 * @Version: V_1.0.0
 */
@Controller
@RequestMapping("/all")
public class UserAllContruller {

    @Autowired
    private UserService userService;



    @RequestMapping("/show")
    @ResponseBody
    public Map<String , Object> show(Integer page, Integer rows) {
        System.out.println("展示数据的action");
        List<User> data = userService.selectAllUser(page,rows);// page页下的rows条数据
        Integer records = userService.selectCount();//当前数据源下的总数据数
        Integer total = records%rows!=0?(records/rows+1):records/rows;
        System.out.println(data);
        System.out.println(total);
        System.out.println(records);
        Map<String, Object> map = new BaseNorms().setResult(data, page, total, records);
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, String> edit(String oper, User user){
        Map<String,String> map=new HashMap<String,String>();
        if("add".equals(oper)){
            System.out.println("添加的信息："+user);
            user.setId(UUID.randomUUID().toString());
            user.setPassword("123123");
            System.out.println(user);
            userService.insUser(user);
            map.put("id",user.getId());

        }else if("del".equals(oper)){
            userService.delUser(user.getId());
            System.out.println("实现删除");
        }else{
            System.out.println(user);
            userService.updateUser(user);
            System.out.println("数据修改成功");
            map.put("id",user.getId());
        }
        map.put("status","ok");
        return map;
    }
}
