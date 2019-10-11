package com.bbawker.webservice.dto.ETOAdmin;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccounts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtoAccountsSaveRequestDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String subwayByLine;
    private String station_cd;
    private String station_nm;

    public EtoAccounts toEntity(){
        return EtoAccounts.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .subwayByLine(subwayByLine)
                .station_cd(station_cd)
                .station_nm(station_nm)
                .build();
    }

}
