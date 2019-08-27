package com.bbawker.webservice.dto.question;

import com.bbawker.webservice.domain.Question.Question;
import com.bbawker.webservice.domain.accounts.Accounts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSaveRequestDto {

    private String title;
    private String question;

    @Builder
    public QuestionSaveRequestDto(String title, String question) {
        this.title = title;
        this.question = question;
    }

    public Question toEntity(){
        return Question.builder()
                .title(title)
                .question(question)
                .build();
    }

}
