package com.bbawker.webservice.ETOAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/easyAdmin")
public class ETOChatController {

    @GetMapping("/chat")
    public String chat(Model model){
        model.addAttribute("parent_menu", "chat");

        return "/ETOAdmin/chat";
    }
}
