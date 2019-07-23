package com.bbawker.webservice.dto.accounts;


import com.bbawker.webservice.domain.accounts.Accounts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountsSaveRequestDto {

    private String name;
    private String email;
    private String password;

    @Builder
    public AccountsSaveRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Accounts toEntity(){
        return Accounts.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    @Override
    public String toString() {
        return "AccountsSaveRequestDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
