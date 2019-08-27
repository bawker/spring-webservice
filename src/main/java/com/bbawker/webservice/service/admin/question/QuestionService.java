package com.bbawker.webservice.service.admin.question;

import com.bbawker.webservice.domain.Question.QuestionRepository;

import com.bbawker.webservice.dto.question.QuestionSaveRequestDto;
import com.bbawker.webservice.web.HttpSessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto dto){
        System.out.println("123333");
        return questionRepository.save(dto.toEntity()).getSeq();
    }

//    @Transactional(readOnly = true)
//    public List<AccountsMainResponseDto> LoginProc(AccountsSaveRequestDto dto) {
//        return accountsRepository.findByEmail(dto.getEmail())
//                .map(AccountsMainResponseDto::new)
//                .collect(Collectors.toList());
//    }


}
