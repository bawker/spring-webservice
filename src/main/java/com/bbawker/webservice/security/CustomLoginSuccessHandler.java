package com.bbawker.webservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** 로그인 성공 시, 리다이렉트되기 전의 페이지로 이동시켜준다거나 하는 부가작업을 추가하기 위해서는
 * SecurityConfig의 successHandler에 핸들러를 등록해주어야 한다, 등록할 핸들러의 타입은
 * AuthenticationSuccessHandler 타입이기 때문에 AuthenticationSuccessHandler를 구현하면서 로그인 성공시
 * 사용되는 핸들러인 SavedRequestAwareAuthenticationSuccessHandler를 상속받아 구현하고 SecurityConfig에 등록시켜 주면 된다.
 *
 * 이전 페이지 정보를 받아 Redirect하는 SavedRequestAwareAuthenticationSuccessHandler 구현하기
 * AuthenticationSuccessHandler인터페이스를 구현하는 SimpleUrlAuthenticationSuccessHandler를
 * 상속하는 SavedRequestAwareAuthenticationSuccessHandler를 상속해서 구현하면 된다.
 *
 * @GetMapping("/login") 에서 session에 이전 페이지 정보 담아둠
 */

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public CustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {


            String redirectUrl = (String) session.getAttribute("prevPage");
            if (redirectUrl != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
