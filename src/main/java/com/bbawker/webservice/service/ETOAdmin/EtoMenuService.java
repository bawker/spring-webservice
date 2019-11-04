package com.bbawker.webservice.service.ETOAdmin;

import com.bbawker.webservice.domain.ETOAdmin.EtoMenuRepository;
import com.bbawker.webservice.dto.ETOAdmin.EtoMenuSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class EtoMenuService {
    private EtoMenuRepository etoMenuRepository;

    @Transactional
    public Long save(EtoMenuSaveRequestDto dto, MultipartFile file) {

        return etoMenuRepository.save(dto.toEntity()).getId();
    }

}
