package com.bbawker.webservice.domain.ETOAdmin;

import com.bbawker.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class EtoAccountsRole extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String roleName;

    @Builder
    public EtoAccountsRole(String roleName) {
        this.roleName = roleName;
    }

}
