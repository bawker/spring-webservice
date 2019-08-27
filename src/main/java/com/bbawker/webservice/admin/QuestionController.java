package com.bbawker.webservice.admin;

import com.bbawker.webservice.dto.question.QuestionSaveRequestDto;
import com.bbawker.webservice.service.admin.question.QuestionService;
import com.bbawker.webservice.web.HttpSessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {

    private QuestionService questionService;

    @GetMapping("/admin/question")
    public String form(HttpSession session, Model model){
        boolean result = HttpSessionUtils.loginCheck(session);

        if(result == false) {
            return "redirect:/admin/login";
        }

        model.addAttribute("menu", "question");

        return "admin/question/form";
    }

    @PostMapping("/admin/writeQuestion")
    public String writeQuestion(QuestionSaveRequestDto dto){

        QuestionSaveRequestDto test = dto;
        questionService.save(dto);


        return "redirect:/admin/question";
    }

}
