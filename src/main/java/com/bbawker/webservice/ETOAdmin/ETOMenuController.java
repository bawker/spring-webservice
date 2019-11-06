package com.bbawker.webservice.ETOAdmin;

import com.bbawker.webservice.config.UploadPath;
import com.bbawker.webservice.domain.ETOAdmin.EtoAccounts;
import com.bbawker.webservice.domain.ETOAdmin.EtoMenu;
import com.bbawker.webservice.dto.ETOAdmin.EtoMenuSaveRequestDto;
import com.bbawker.webservice.service.ETOAdmin.EtoAccountsService;
import com.bbawker.webservice.service.ETOAdmin.EtoMenuService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
public class ETOMenuController {

    private EtoMenuService etoMenuService;
    private EtoAccountsService etoAccountsService;

    UploadPath uploadPath;

    @GetMapping("/createMenu")
    public String createMenu(Model model) {
        model.addAttribute("parent_menu", "menu");
        model.addAttribute("child_menu", "create");

        return "/ETOAdmin/createMenu";
    }

    @PostMapping("/createMenu")
    public String createMenuProc(EtoMenuSaveRequestDto dto, MultipartFile file, Principal principal) {
        String UPLOADPATH = uploadPath.getPath();
        EtoAccounts etoAccounts = etoAccountsService.findetoAccounts(principal.getName());

        try {
            String fileNm = file.getOriginalFilename();
            String filePath = UPLOADPATH + File.separator + fileNm;

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();

        } catch (Exception e) {

        }

        dto.setFileName(file.getOriginalFilename());
        dto.setFileType(file.getContentType());
        dto.setUploadPath("/static/image/");
        dto.setEtoAccounts(etoAccounts);

        etoMenuService.save(dto, file);

        return "redirect:/easyAdmin";
    }

    @GetMapping("listMenu")
    public String listMenu(Model model, Principal principal) {
        model.addAttribute("parent_menu", "menu");
        model.addAttribute("child_menu", "list");

        EtoAccounts etoAccounts = etoAccountsService.findetoAccounts(principal.getName());

        model.addAttribute("eto_menus", etoMenuService.findAll(etoAccounts));

        return "/ETOAdmin/listMenu";
    }

}
