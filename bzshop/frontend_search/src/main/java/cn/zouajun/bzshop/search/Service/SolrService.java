package cn.zouajun.bzshop.search.Service;

import cn.zouajun.bzshop.utils.Result;
import cn.zouajun.bzshop.utils.SolrDocument;

import java.util.List;

public interface SolrService {
    Result importAll();

    List<SolrDocument> selectByQ(String q, Long page, Integer pageSize);
}
