package cn.zouajun.bzshop.frontend.portal.controller;

import cn.zouajun.bzshop.frontend.portal.service.ContentService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD(){
        //System.out.println("aa");
        try{
            return contentService.selectFrontendContentByAD();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  Result.build(500,"error");
    }
}
