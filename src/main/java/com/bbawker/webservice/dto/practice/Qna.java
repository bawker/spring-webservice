package com.bbawker.webservice.dto.practice;

import com.bbawker.webservice.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Qna extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_qna_writer"))
    private User writer;

    @OneToMany(mappedBy = "qna")
    @OrderBy("id ASC")
    @JsonIgnore
    private List<Answer> answers;

    private String title;

    @Lob
    private String content;

    //JPA는 기본 생성자가 있었야함
    public Qna(){}

    public Qna(User writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public boolean isSameWriter(User loginUser) {
        return this.writer.equals(loginUser);
    }
}
