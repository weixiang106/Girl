package com.example.demo;
import com.example.demo.utils.CodeJsonTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.TextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;

@RestController
@Api(value = "个人中心")
@RequestMapping("/v1")
public class TestController {
     @Autowired
     private ArticleRepository articleRepository;
     @Autowired
     private StuRepository stuRepository;
    @ApiOperation(value = "获取所有用户数据" ,  notes="获取用户")
    @GetMapping(value = "/getAllValues")
    public String getList(){
        List<Article> articleList = articleRepository.findAll();
        return CodeJsonTools.success(articleList);
    }

    /**
     * 添加一个记录
     *
     */
     @ApiOperation(value = "添加用户信息" ,  notes="添加用户信息")
     @PostMapping(value = "/addArticle")
     public Article addStu(
             @RequestParam("title") String title,
             @RequestParam("summary") String summary,
             @RequestParam("userId")Long userId,
             @RequestParam("status") Integer status){
               Article article =new Article();
               article.setTitle(title);
               article.setSummary(summary);
               article.setUserId(userId);
               article.setStatus(status);
               return articleRepository.save(article);
            }

    /**
     * 添加一个记录
     *
     */
    @ApiOperation(value = "用户数据添加" ,  notes="添加用户")
    @PostMapping(value = "/addStudent")
    public Stu addStudent(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("description")String description){
        Stu stu =new Stu();
        stu.setId(id);
        stu.setName(name);
        stu.setDescription(description);
        return stuRepository.save(stu);
    }
    /**
     * 根据id删除
     * @param id
     */
    @ApiOperation(value = "根据ID删除数据" ,  notes="删除数据")
    @DeleteMapping(value = "/student/{id}")
    public void girlDelete(@PathVariable("id")Integer id){
        stuRepository.deleteById(id);
    }



    @ApiOperation(value = "获取所有数据" ,  notes="获取用户")
    @GetMapping(value = "/getAllStu")
    public String getListStu(){
        List<Stu> stuList = stuRepository.findAll();
        return CodeJsonTools.success(stuList);
    }
    @ApiOperation(value = "上传图片",notes = "图片上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upLoad(HttpServletRequest request, MultipartFile file){
        try {
            String pathDir = request.getSession().getServletContext().getRealPath("/")+ "/upload";
            File dir = new File(pathDir);
            if(!dir.exists()){
                dir.mkdir();
            }
            String suffix = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf("."));
            if(suffix.equals("") || suffix == null){
                return "上傳文件不能為空";
            }
            String fileName = UUID.randomUUID() + suffix;
            File serverFile = new File(pathDir + fileName);
            file.transferTo(serverFile);
            System.out.println(fileName);

        }catch (Exception e){
            e.printStackTrace();
            return CodeJsonTools.success("上傳失敗");
        }
        return CodeJsonTools.success("上傳成功");
    }

   private void executeUpload(String fileDir,MultipartFile file) throws Exception{
       String suffix = file.getOriginalFilename()
               .substring(file.getOriginalFilename().lastIndexOf("."));
       String fileName = UUID.randomUUID() + suffix;
       File serverFile = new File(fileDir + fileName);
       file.transferTo(serverFile);
   }

    @ApiOperation(value = "多图片上传",notes = "多图上传")
    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public @ResponseBody String upLoads(HttpServletRequest request,MultipartFile[] files){
         try {
             String pathDir = request.getSession().getServletContext().getRealPath("/")+ "/upload";
             File dir = new File(pathDir);
             if(!dir.exists()){
                 dir.mkdir();
             }
             for (int i = 0; i <files.length ; i++) {
                 if(files[i]  != null) {
                   executeUpload(pathDir,files[i]);
                 }
             }

         }catch (Exception e){
             e.printStackTrace();
             return CodeJsonTools.success("上傳失敗");
         }
        return CodeJsonTools.success("上傳成功");
   }


}
