package com.bbawker.webservice.dto.ETOAdmin;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccountsRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtoAccountsRoleSaveRequestDto {
    private Long id;
    private String roleName;

    public EtoAccountsRole toEntity(){
        return EtoAccountsRole.builder()
                .roleName(roleName)
                .build();
    }

}
