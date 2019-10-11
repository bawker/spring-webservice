package com.bbawker.webservice.practice;

import com.bbawker.webservice.domain.practice.AnswerRepository;
import com.bbawker.webservice.domain.practice.QnaRepository;
import com.bbawker.webservice.dto.practice.Answer;
import com.bbawker.webservice.dto.practice.Qna;
import com.bbawker.webservice.dto.practice.User;
import com.bbawker.webservice.web.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
    @Autowired
    private QnaRepository qnaRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("")
    public Answer create(@PathVariable Long questionId, String contents, HttpSession session){
        if(!HttpSessionUtils.loginCheck(session)) {
            return null;
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Qna qna = qnaRepository.findOne(questionId);
        Answer answer = new Answer(loginUser, qna, contents);

        return answerRepository.save(answer);

    }

    @DeleteMapping("{id}")
    public Map<String, String> delete(@PathVariable Long id, HttpSession session){
        Map<String, String> map = new HashMap<String, String>();

        if(!HttpSessionUtils.loginCheck(session)) {
            map.put("result", "fail");
            map.put("massage", "로그인 안됨");
            return map;
        }

        User loginUser = HttpSessionUtils.getUserFromSession(session);
        Answer answer = answerRepository.findOne(id);

        if(!answer.isSameWriter(loginUser)) {
            map.put("result", "fail");
            map.put("massage", "자신의 글만 삭제 가능");
            return map;
        }

        answerRepository.delete(id);
        map.put("result", "success");
        return map;
    }


}
