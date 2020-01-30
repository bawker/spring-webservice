package com.bbawker.webservice.ETOAdmin;

import com.bbawker.webservice.config.UploadPath;
import com.bbawker.webservice.domain.ETOAdmin.EtoAccounts;
import com.bbawker.webservice.domain.ETOAdmin.EtoMenu;
import com.bbawker.webservice.domain.ETOAdmin.EtoMenuRepository;
import com.bbawker.webservice.dto.ETOAdmin.EtoAccountsSaveRequestDto;
import com.bbawker.webservice.dto.ETOAdmin.EtoMenuSaveRequestDto;
import com.bbawker.webservice.service.ETOAdmin.EtoAccountsService;
import com.bbawker.webservice.service.ETOAdmin.EtoMenuService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        String fileName = null;
        String fileType = null;
        String uploadPath = null;

        if(file != null && !file.getOriginalFilename().equals("")) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhmmss");
            String formattedDate = sdf.format(date);

            //WebConfig에서 /static/image/** 요청이 오면 application.yml에 있는 upload.path 쪽으로 연결해줌
            try {
                fileName = formattedDate + "_" + file.getOriginalFilename();
                fileType = file.getContentType();
                uploadPath = "/static/image/";
                String filePath = UPLOADPATH + File.separator + fileName;

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();

            } catch (Exception e) {

            }
        }

        dto.setFileName(fileName);
        dto.setFileType(fileType);
        dto.setUploadPath(uploadPath);
        dto.setEtoAccounts(etoAccounts);

        etoMenuService.save(dto);

        return "redirect:/easyAdmin";
    }

    @GetMapping("/listMenu")
    public String listMenu(Model model, Principal principal) {
        model.addAttribute("parent_menu", "menu");
        model.addAttribute("child_menu", "list");

        EtoAccounts etoAccounts = etoAccountsService.findetoAccounts(principal.getName());

        model.addAttribute("eto_menus", etoMenuService.findAll(etoAccounts));

        return "/ETOAdmin/listMenu";
    }

    @GetMapping("/detail/{id}")
    public String detailMenu(Model model, @PathVariable Long id, Principal principal, HttpServletResponse response) throws Exception{
        model.addAttribute("parent_menu", "menu");
        model.addAttribute("child_menu", "list");

        EtoAccounts etoAccounts = etoAccountsService.findetoAccounts(principal.getName());
        EtoMenu etoMenu = etoMenuService.findEtoMenuByOne(etoAccounts, id);

        if(etoMenu == null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('자신의 메뉴만 확인 가능합니다.'); location.href='/easyAdmin/listMenu';</script>");
            out.flush();
        }

        model.addAttribute("eto_menu", etoMenu);

        return "/ETOAdmin/detailMenu";
    }

    @PutMapping("/detail/{id}")
    public String updateMenu(@PathVariable Long id, EtoMenuSaveRequestDto dto, MultipartFile file, Principal principal) {
        String UPLOADPATH = uploadPath.getPath();
        EtoAccounts etoAccounts = etoAccountsService.findetoAccounts(principal.getName());

        String fileName = null;
        String fileType = null;
        String uploadPath = null;

        if(file == null) {

        } else if (file.getOriginalFilename().equals("")){
            EtoMenu etoMenu = etoMenuService.findEtoMenuByOne(etoAccounts, id);
            fileName = etoMenu.getFileName();
            fileType = etoMenu.getFileType();
            uploadPath = etoMenu.getUploadPath();
        } else if(!file.getOriginalFilename().equals("")) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhmmss");
            String formattedDate = sdf.format(date);

            //WebConfig에서 /static/image/** 요청이 오면 application.yml에 있는 upload.path 쪽으로 연결해줌
            try {
                fileName = formattedDate + "_" + file.getOriginalFilename();
                fileType = file.getContentType();
                uploadPath = "/static/image/";
                String filePath = UPLOADPATH + File.separator + fileName;

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();

            } catch (Exception e) {

            }

        }

        dto.setFileName(fileName);
        dto.setFileType(fileType);
        dto.setUploadPath(uploadPath);

        dto.setEtoAccounts(etoAccounts);

        etoMenuService.save(dto);

        return "redirect:/easyAdmin/listMenu";
    }

    @GetMapping("/deleteMenu/{id}")
    public String deleteMenu(@PathVariable Long id, Principal principal, HttpServletResponse response) throws Exception{
        EtoAccounts etoAccounts = etoAccountsService.findetoAccounts(principal.getName());
        EtoMenu etoMenu = etoMenuService.findEtoMenuByOne(etoAccounts, id);

        if(etoMenu == null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('자신의 메뉴만 삭제 가능합니다.'); location.href='/easyAdmin/listMenu'; </script>");
            out.flush();

        } else {
            etoMenuService.delete(id);
        }

        return "redirect:/easyAdmin/listMenu";
    }

}
