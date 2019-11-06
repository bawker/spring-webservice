package com.bbawker.webservice.domain.ETOAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtoMenuRepository extends JpaRepository<EtoMenu, Long> {
    @Query("SELECT e FROM EtoMenu e WHERE e.etoAccounts = :etoAccounts")
    List<EtoMenu> findEtoMenuByAll(@Param("etoAccounts") EtoAccounts etoAccounts);
}
