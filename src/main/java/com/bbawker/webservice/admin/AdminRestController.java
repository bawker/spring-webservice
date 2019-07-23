package com.bbawker.webservice.admin;

import com.bbawker.webservice.dto.accounts.AccountsSaveRequestDto;
import com.bbawker.webservice.service.admin.accounts.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminRestController {

    private AccountsService accountsService;

    @PostMapping("/admin/test")
    public String test(@RequestBody AccountsSaveRequestDto dto)
    {
        return "test";
    }

    @PostMapping("/admin/createAccount")
    public Long saveAccounts(@RequestBody AccountsSaveRequestDto dto) {
        return accountsService.save(dto);
    }



}
