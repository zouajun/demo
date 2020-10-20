package cn.zouajun.bzshop.backend.item.controller;

import cn.zouajun.bzshop.backend.item.service.FileUploadService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("/upload")
    public Result fileUpload(MultipartFile file){

        try{
            return this.fileUploadService.fileUpload(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
