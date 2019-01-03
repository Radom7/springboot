package com.haiyu.controller;

import com.lin.entity.Blog;
import com.lin.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: MainController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/1 14:56
 */
@Controller
public class MainController {

    private static String UPLOADED_FOLDER = "E:\\images\\";
    @Autowired
    BlogRepository blogRepository;

    @RequestMapping("edit")
    public String Hello(){
        return "edit";
    }

    @RequestMapping("submit")
    @ResponseBody
    public void    submit(Blog blog){
        System.out.println(blog.getContent());
        System.out.println(blog.getHtmlContent());
        blogRepository.save(blog);
    }

    @RequestMapping("view/{ids}")
    public  String view(Model model,@PathVariable String ids){
        System.out.println(ids);
        Long id = Long.parseLong(ids);
        System.out.println(id);
        Blog blog =   blogRepository.findBlogById(id);
        System.out.println(blog.getHtmlContent());
        model.addAttribute("blog",blog);
        return  "view";
    }
    //处理文件上传
    @RequestMapping(value="/uploadimg")
    public @ResponseBody Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String fileName = file.getOriginalFilename();
        //保存
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url","../images/"+fileName);
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        System.out.println(resultMap.get("success"));
        return resultMap;
    }
}
