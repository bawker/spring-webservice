package com.bbawker.webservice.service.admin.accounts;

import com.bbawker.webservice.domain.accounts.Accounts;
import com.bbawker.webservice.domain.accounts.AccountsRepository;
import com.bbawker.webservice.dto.accounts.AccountsMainResponseDto;
import com.bbawker.webservice.dto.accounts.AccountsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountsService {

    private AccountsRepository accountsRepository;

    @Transactional
    public Long save(AccountsSaveRequestDto dto){
        return accountsRepository.save(dto.toEntity()).getSeq();
    }

//    @Transactional(readOnly = true)
//    public List<AccountsMainResponseDto> LoginProc(AccountsSaveRequestDto dto) {
//        return accountsRepository.findByEmail(dto.getEmail())
//                .map(AccountsMainResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public Accounts LoginProc(AccountsSaveRequestDto dto) {
        return accountsRepository.findByEmail(dto.getEmail());
    }

}
