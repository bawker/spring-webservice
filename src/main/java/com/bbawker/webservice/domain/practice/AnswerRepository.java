package com.bbawker.webservice.domain.practice;

import com.bbawker.webservice.dto.practice.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
