package com.bbawker.webservice.domain.practice;

import com.bbawker.webservice.dto.practice.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {
}
