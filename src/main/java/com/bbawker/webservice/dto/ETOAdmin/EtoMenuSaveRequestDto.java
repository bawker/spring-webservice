package com.bbawker.webservice.dto.ETOAdmin;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccounts;
import com.bbawker.webservice.domain.ETOAdmin.EtoMenu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtoMenuSaveRequestDto {
    private Long id;
    private String title;
    private Long price;
    private String description;
    private String fileName;
    private String fileType;
    private String uploadPath;
    private byte[] data;
    private EtoAccounts etoAccounts;

    public EtoMenu toEntity(){
        return EtoMenu.builder()
                .id(id)
                .title(title)
                .price(price)
                .description(description)
                .fileName(fileName)
                .fileType(fileType)
                .uploadPath(uploadPath)
                .data(data)
                .etoAccounts(etoAccounts)
                .build();
    }

    public EtoMenu updateEntity(){
        return EtoMenu.builder()
                .title(title)
                .price(price)
                .description(description)
                .fileName(fileName)
                .fileType(fileType)
                .uploadPath(uploadPath)
                .data(data)
                .etoAccounts(etoAccounts)
                .build();
    }

}
