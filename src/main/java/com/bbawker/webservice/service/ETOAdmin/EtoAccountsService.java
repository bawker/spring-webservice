package com.bbawker.webservice.service.ETOAdmin;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccountsRepository;
import com.bbawker.webservice.domain.ETOAdmin.EtoAccountsRole;
import com.bbawker.webservice.dto.ETOAdmin.EtoAccountsRoleSaveRequestDto;
import com.bbawker.webservice.dto.ETOAdmin.EtoAccountsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class EtoAccountsService {
    private EtoAccountsRepository etoAccountsRepository;

    @Transactional
    public Long save(EtoAccountsSaveRequestDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        EtoAccountsRoleSaveRequestDto role = new EtoAccountsRoleSaveRequestDto();

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        role.setRoleName("ADMIN");

        dto.setRoles(Arrays.asList(role.toEntity()));

        return etoAccountsRepository.save(dto.toEntity()).getId();
    }
}
