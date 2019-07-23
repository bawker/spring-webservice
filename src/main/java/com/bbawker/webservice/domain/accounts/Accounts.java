package com.bbawker.webservice.domain.accounts;

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
public class Accounts extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    @Builder
    public Accounts(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
