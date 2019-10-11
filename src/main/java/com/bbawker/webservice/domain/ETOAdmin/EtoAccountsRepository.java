package com.bbawker.webservice.domain.ETOAdmin;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 paRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성 됨
**/

public interface EtoAccountsRepository extends JpaRepository<EtoAccounts, Long> {
}
