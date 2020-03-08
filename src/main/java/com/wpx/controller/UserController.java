package com.wpx.controller;

import com.wpx.pojo.User;
import com.wpx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author: wpx
 * @Date: 2020/3/2 13:40
 * @Version: V_1.0.0
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"login","message"})
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login(Model model,User user) {
        try {
            System.out.println(user);
            user = userService.selectUser(user);
        } catch (Exception e) {
            String message=e.getMessage();
            model.addAttribute("message",message);
            return "/index";
        }
        model.addAttribute("login",user);
        return "redirect:/views/show.jsp";
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:/index.jsp";
    }


}
