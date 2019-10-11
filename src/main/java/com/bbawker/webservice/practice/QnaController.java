package com.bbawker.webservice.practice;

import com.bbawker.webservice.domain.Question.Question;
import com.bbawker.webservice.domain.practice.QnaRepository;
import com.bbawker.webservice.dto.practice.Qna;
import com.bbawker.webservice.dto.practice.User;
import com.bbawker.webservice.web.HttpSessionUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QnaController {

    @Autowired
    private QnaRepository questionRepository;

    @GetMapping("form")
    public String form(HttpSession session){
        if(!HttpSessionUtils.loginCheck(session)) {
            return "redirect:/user/loginForm";
        }

        return "/practice/qna/form";
    }

    @PostMapping("")
    public String create(String title, String content, HttpSession session) {
        if(!HttpSessionUtils.loginCheck(session)) {
            return "redirect:/user/loginForm";
        }

        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        Qna newQuestion = new Qna(sessionUser, title, content);

        questionRepository.save(newQuestion);

        return "redirect:/questions/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("questions", questionRepository.findAll());

        return "/practice/qna/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionRepository.findOne(id));

        return "/practice/qna/detail";
    }

    @GetMapping("/form/{id}")
    public String updateQuestion(@PathVariable Long id, Model model, HttpSession session) {
        if(!HttpSessionUtils.loginCheck(session)) {
            return "redirect:/user/loginForm";
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Qna qna = questionRepository.findOne(id);

        if(!qna.isSameWriter(loginUser)) {
            return "redirect:/user/loginForm";
        }
        model.addAttribute("question", qna);
        return "/practice/qna/updateForm";
    }

    @PutMapping("/update/{id}")
    public String updateQuestionProc(@PathVariable Long id, String title, String content, HttpSession session) {
        if(!HttpSessionUtils.loginCheck(session)) {
            return "redirect:/user/loginForm";
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Qna qna = questionRepository.findOne(id);

        if(!qna.isSameWriter(loginUser)) {
            return "redirect:/user/loginForm";
        }

        qna.update(title, content);
        questionRepository.save(qna);
        return "redirect:/questions/detail/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id, HttpSession session){
        if(!HttpSessionUtils.loginCheck(session)) {
            return "redirect:/user/loginForm";
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Qna qna = questionRepository.findOne(id);

        if(!qna.isSameWriter(loginUser)) {
            return "redirect:/user/loginForm";
        }

        questionRepository.delete(id);
        return "redirect:/questions/list";
    }

}
