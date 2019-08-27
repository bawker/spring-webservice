package com.bbawker.webservice.domain.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {

//    Accounts findByEmail(String email);

//    @Query("SELECT a FROM Accounts a WHERE a.email = :email")
//    Accounts findByEmail(@Param("email") String email);

}
