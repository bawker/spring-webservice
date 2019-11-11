package com.bbawker.webservice.service.ETOAdmin;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccounts;
import com.bbawker.webservice.domain.ETOAdmin.EtoMenu;
import com.bbawker.webservice.domain.ETOAdmin.EtoMenuRepository;
import com.bbawker.webservice.dto.ETOAdmin.EtoMenuSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class EtoMenuService {
    private EtoMenuRepository etoMenuRepository;

    @Transactional
    public Long save(EtoMenuSaveRequestDto dto) {

        return etoMenuRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<EtoMenu> findAll(EtoAccounts etoAccounts) {
//        return etoMenuRepository.findAll();
        return etoMenuRepository.findEtoMenuByAll(etoAccounts);
    }

    @Transactional(readOnly = true)
    public EtoMenu findEtoMenuByOne(EtoAccounts etoAccounts, Long id) {
        return etoMenuRepository.findEtoMenuByOne(etoAccounts, id);
    }

}
