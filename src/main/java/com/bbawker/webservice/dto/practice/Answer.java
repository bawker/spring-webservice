package com.bbawker.webservice.dto.practice;

import com.bbawker.webservice.domain.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_qna"))
    private Qna qna;

    @Lob
    private String contents;

    public Answer(){

    }

    public Answer(User writer, Qna qna, String contents) {
        this.writer = writer;
        this.qna = qna;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", writer=" + writer +
                ", contents='" + contents + '\'' +
                '}';
    }

    public boolean isSameWriter(User loginUser) {
        return this.writer.equals(loginUser);
    }
}
