package com.bbawker.webservice.practice;

import com.bbawker.webservice.dto.practice.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> users = new ArrayList<User>();

    @GetMapping("/join")
    public String join(){
        return "practice/join";
    }

    @PostMapping("/create")
    public String create(User user) {
        System.out.println("user : " + user);
        users.add(user);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", users);
//        model.addAttribute("users", users);
        return "practice/list";
    }
}
