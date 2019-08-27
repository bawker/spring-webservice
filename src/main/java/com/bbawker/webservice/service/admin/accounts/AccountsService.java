package com.bbawker.webservice.service.admin.accounts;

import com.bbawker.webservice.domain.accounts.Accounts;
import com.bbawker.webservice.domain.accounts.AccountsRepository;
import com.bbawker.webservice.dto.accounts.AccountsMainResponseDto;
import com.bbawker.webservice.dto.accounts.AccountsSaveRequestDto;
import com.bbawker.webservice.web.HttpSessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
    public boolean LoginProc(AccountsSaveRequestDto dto, HttpSession session) {
        Accounts user = accountsRepository.findByEmail(dto.getEmail());
        if(user == null) return false;
        if(!dto.getPassword().equals(user.getPassword())) return false;

        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);

        return true;
    }

    public boolean LogoutProc(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return true;
    }

}
