package com.bbawker.webservice.domain.practice;

import com.bbawker.webservice.dto.practice.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
