package com.example.demo.controller;

import com.example.demo.service.UploadServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
@Controller
@RequestMapping(value = "/upload")
public class FileUploadController {

    @Autowired
    private UploadServiceI uploadService;

    @RequestMapping(value = "/uploadImg")
    public void uploadImg(MultipartFile[] imgs){
        try{
            uploadService.transportUploadsToDisk(imgs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
