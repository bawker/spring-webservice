package com.bbawker.webservice.ETOAdmin;

import com.bbawker.webservice.config.UploadPath;
import com.bbawker.webservice.dto.ETOAdmin.EtoMenuSaveRequestDto;
import com.bbawker.webservice.service.ETOAdmin.EtoMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
public class ETOMenuController {

    private EtoMenuService etoMenuService;

    UploadPath uploadPath;

//    private final String UPLOADPATH = "C:\\test";

    @GetMapping("/createMenu")
    public String createMenu(Model model) {
        model.addAttribute("menu", "createMenu");

        return "/ETOAdmin/createMenu";
    }

    @PostMapping("/createMenu")
    public String createMenuProc(EtoMenuSaveRequestDto dto, MultipartFile file) {
        String UPLOADPATH = uploadPath.getPath();

        try {
            String fileNm = file.getOriginalFilename();
            String filePath = UPLOADPATH + File.separator + fileNm;

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();

        } catch (Exception e) {

        }

        //etoMenuService.save(dto, file);

        return "redirect:/easyAdmin";
    }

}
