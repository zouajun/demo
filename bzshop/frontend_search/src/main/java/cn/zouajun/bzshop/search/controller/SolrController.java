package cn.zouajun.bzshop.search.controller;

import cn.zouajun.bzshop.search.Service.SolrService;
import cn.zouajun.bzshop.utils.Result;
import cn.zouajun.bzshop.utils.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SolrController {

    @Autowired
    SolrService service;

    /*
    * 向solr索引库中导入数据
    * */
    @RequestMapping("/importAll")
    public Result importAll(){
        return service.importAll();
    }

    /*
    * 搜索数据
    * */
    @RequestMapping("/list")
    public List<SolrDocument> selectByQ(String q, @RequestParam(defaultValue = "1") Long page, @RequestParam(defaultValue = "10") Integer pageSize){
        System.out.println(q);
        try {
            return service.selectByQ(q,page,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
