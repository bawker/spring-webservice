package com.bbawker.webservice.practice;

import com.bbawker.webservice.domain.practice.UserRepository;
import com.bbawker.webservice.dto.practice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "/practice/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session){
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            return "redirect:/user/loginForm";
        }

        if(!password.equals(user.getPassword())) {
            return "redirect:/user/loginForm";
        }

        session.setAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping("/join")
    public String join(){
        return "practice/join";
    }

    @PostMapping("/create")
    public String create(User user) {
        System.out.println("user : " + user);
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userRepository.findAll());

        return "practice/list";
    }

    @GetMapping("/form/{id}")
    public String updateUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userRepository.findOne(id));
        return "/practice/userForm";
    }

    @PutMapping("/update/{id}")
    public String updateUserProc(@PathVariable Long id, User newUser){
        User user = userRepository.findOne(id);
        user.update(newUser);
        userRepository.save(user);
        return "redirect:/user/list";
    }
}
