package com.bbawker.webservice.dto.accounts;

import com.bbawker.webservice.domain.accounts.Accounts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class AccountsMainResponseDto {
    private String email;
    private String name;
    private String password;
    private String modifiedDate;

    public String getPassword() {
        return password;
    }

    public AccountsMainResponseDto(Accounts entity) {
        email = entity.getEmail();
        name = entity.getName();
        password = entity.getPassword();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    /**
     * Java 8 버전
     */
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

    /**
     * Java 7 버전
     */
    private String toStringDateTimeByJava7(LocalDateTime localDateTime){
        if(localDateTime == null){
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(localDateTime);
    }

    public AccountsMainResponseDto(String email, String name, String password, String modifiedDate) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.modifiedDate = modifiedDate;
    }
}