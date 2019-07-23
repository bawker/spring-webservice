package com.bbawker.webservice.admin;

import com.bbawker.webservice.domain.accounts.Accounts;
import com.bbawker.webservice.dto.accounts.AccountsSaveRequestDto;
import com.bbawker.webservice.service.admin.accounts.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private AccountsService accountsService;

    @GetMapping("/admin")
    public String main() {

        return "admin/index";
    }

    @GetMapping("/admin/login")
    public String login() {

        return "admin/login";
    }

    @GetMapping("/admin/createAccount")
    public String createAccount() {

        return "admin/createAccount";
    }

    @PostMapping("/admin/login")
    public String loginAccount(AccountsSaveRequestDto dto, HttpSession session) {
        Accounts user = accountsService.LoginProc(dto);

        if(user == null) {
            return "redirect:/admin/login";
        }

        if(!dto.getPassword().equals(user.getPassword())) {
            return "redirect:/admin/login";
        }

        session.setAttribute("user", user);

        return "redirect:/admin";
    }
}
