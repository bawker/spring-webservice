package com.bbawker.webservice.practice;

import com.bbawker.webservice.domain.practice.UserRepository;
import com.bbawker.webservice.dto.practice.User;
import com.bbawker.webservice.web.HttpSessionUtils;
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

        if(!user.matchPassword(password)) {
            return "redirect:/user/loginForm";
        }

        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);

        return "redirect:/questions/list";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);

        return "redirect:/user/loginForm";
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
    public String updateUser(@PathVariable Long id, Model model, HttpSession session){
        if(!HttpSessionUtils.loginCheck(session)) {
            return "redirect:/user/loginForm";
        }

        User sessionedUser = HttpSessionUtils.getUserFromSession(session);

        if(!sessionedUser.matchId(id)){
            throw new IllegalStateException("You can't update the another user");
        }
        model.addAttribute("user", userRepository.findOne(id));
        return "/practice/userForm";
    }

    @PutMapping("/update/{id}")
    public String updateUserProc(@PathVariable Long id, User updatedUser, HttpSession session){
        if(!HttpSessionUtils.loginCheck(session)) {
            return "redirect:/user/loginForm";
        }

        User sessionedUser = HttpSessionUtils.getUserFromSession(session);

        if(!sessionedUser.matchId(id)){
            throw new IllegalStateException("You can't update the another user");
        }


        User user = userRepository.findOne(id);
        user.update(updatedUser);
        userRepository.save(user);
        return "redirect:/user/list";
    }
}
