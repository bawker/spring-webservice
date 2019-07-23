package com.bbawker.webservice.domain.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

//    Accounts findByEmail(String email);

    @Query("SELECT a FROM Accounts a WHERE a.email = :email")
    Accounts findByEmail(@Param("email") String email);

}
