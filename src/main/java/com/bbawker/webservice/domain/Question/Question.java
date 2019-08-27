package com.bbawker.webservice.domain.Question;

import com.bbawker.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 50, nullable = false)
    private String title;

    private String question;

    @Builder
    public Question(String title, String question) {
        this.title = title;
        this.question = question;
    }


}
