package com.bbawker.webservice.admin;

import com.bbawker.webservice.domain.accounts.Accounts;
import com.bbawker.webservice.dto.accounts.AccountsSaveRequestDto;
import com.bbawker.webservice.service.admin.accounts.AccountsService;
import com.bbawker.webservice.web.HttpSessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String main(HttpSession session, Model model) {

        boolean result = HttpSessionUtils.loginCheck(session);

        if(result == false) {
            return "redirect:/admin/login";
        }

        model.addAttribute("menu", "index");

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
        boolean result = accountsService.LoginProc(dto, session);

        if(result == false) {
            System.out.println("로그인 실패");
            return "redirect:/admin/login";
        }

        System.out.println("로그인 성공");

        return "redirect:/admin";
    }

    @GetMapping("/admin/logout")
    public String logoutAccount(HttpSession session) {
        boolean result = accountsService.LogoutProc(session);

        if(result == true) System.out.println("로그아웃 성공");

        return "redirect:/admin/login";
    }
}
