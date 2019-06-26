package com.bbawker.webservice.web;


import com.bbawker.webservice.domain.posts.PostsRepository;
import com.bbawker.webservice.dto.posts.PostsSaveRequestDto;
import com.bbawker.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ResponseBody -> 메소드에서 리턴되는 값은 View 를 통해서 출력되지 않고 HTTP Response Body 에 직접 쓰여지게 됨
 * @RestController -> @ResponseBody를 모든 메소드에서 적용해줌
 *
 * postsRepository 필드에 @Autowired가 없음 -> 생성자로 주입받는 방식 ->
 * @AllArgsConstructordl 에서 대신 생성해줌 (실제소스는 아래와 같음)
 *      @RestController
 *          public class WebRestController {
 *          private PostsRepository postsRepository;
 *          public WebRestController(PostsRepository postsRepository) {
 *              this.postsRepository = postsRepository;
 *          }
 *          ...
 *      }
*/


@RestController
@AllArgsConstructor
public class WebRestController {

    //private PostsRepository postsRepository;
    private PostsService postsService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

//    @PostMapping("/posts")
//    public void savePosts(@RequestBody PostsSaveRequestDto dto){
//        postsRepository.save(dto.toEntity());
//    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }
}
