package com.bbawker.webservice.ETOAdmin;

import com.bbawker.webservice.dto.ETOAdmin.EtoAccountsSaveRequestDto;
import com.bbawker.webservice.service.ETOAdmin.EtoAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


/**
 * @ResponseBody -> 메소드에서 리턴되는 값은 View 를 통해서 출력되지 않고 HTTP Response Body 에 직접 쓰여지게 됨
 * @RestController -> @ResponseBody를 모든 메소드에서 적용해줌
 *
 * postsRepository 필드에 @Autowired가 없음 -> 생성자로 주입받는 방식 ->
 * @AllArgsConstructor 에서 대신 생성해줌 (실제소스는 아래와 같음)
 *      @RestController
 *          public class WebRestController {
 *          private PostsRepository postsRepository;
 *          public WebRestController(PostsRepository postsRepository) {
 *              this.postsRepository = postsRepository;
 *          }
 *          ...
 *      }
 */

@Controller
@AllArgsConstructor
@RequestMapping("/easyAdmin")
public class ETOController {

    private EtoAccountsService etoAccountsService;

    @GetMapping("")
    public String index(){
        return "/ETOAdmin/index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Principal principal) {
        //Principal 로그인 정보 담겨져있음
        if(principal != null) {
            return "redirect:/easyAdmin";
        }

        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);

        return "ETOAdmin/login";
    }

    @GetMapping("/test")
    public String test() {

        return "/ETOAdmin/login";
    }

    @GetMapping("/createAccount")
    public String createAccount() {

        return "/ETOAdmin/createAccount";
    }

    @PostMapping("/createAccount")
    public  String createAccountProc(EtoAccountsSaveRequestDto dto) {
        Long test = etoAccountsService.save(dto);
        return "redirect:/easyAdmin/createAccount";
    }

}
