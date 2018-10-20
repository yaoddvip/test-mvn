package com.mr.controller;

import com.mr.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yaodd on 2018/10/20.
 */
@Controller
public class PoiController {

    @Autowired
    private PoiService poiService;

    @RequestMapping("exportPoi")
    @ResponseBody
    public String exportPoi(HttpServletResponse response){
        //告诉浏览器 响应的内容，
        response.setContentType("application/x-execl");
        ServletOutputStream outputStream = null;
        try {
            //将接受到的文件，作为一个附件 弹出来 给别人下载  需要设置它的header
            //setHeader("设置响应头","默认的文件名") new String 防止乱码
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户名单.xlsx".getBytes(), "ISO-8859-1"));
            //创建输出流
            outputStream = response.getOutputStream();

            //将流传递给service
            poiService.export(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    //关闭流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "success";
    }

    @RequestMapping("importPoi")
    public String importPoi(MultipartFile file){
        //获取文件名
        String fileName = file.getOriginalFilename();

        poiService.importPoi(fileName , file);
        return "redirect:list";
    }

}
