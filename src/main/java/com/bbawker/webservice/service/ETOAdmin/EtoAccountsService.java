package com.bbawker.webservice.service.ETOAdmin;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccountsRepository;
import com.bbawker.webservice.dto.ETOAdmin.EtoAccountsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class EtoAccountsService {
    private EtoAccountsRepository etoAccountsRepository;

    @Transactional
    public Long save(EtoAccountsSaveRequestDto dto) {
        return etoAccountsRepository.save(dto.toEntity()).getId();
    }
}
