package cn.zouajun.bzshop.search.Service.impl;

import cn.zouajun.bzshop.mapper.SolrItemMapper;
import cn.zouajun.bzshop.pojo.SolrItem;
import cn.zouajun.bzshop.search.Service.SolrService;
import cn.zouajun.bzshop.utils.Result;
import cn.zouajun.bzshop.utils.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrItemMapper solrItemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    @Value("${spring.data.solr.core}")
    private String core;

    @Override
    public Result importAll() {
        try{
            //查询数据
            List<SolrItem> list = this.solrItemMapper.getItemList();
            //将数添加到索引库中
            for (SolrItem i:list) {
                //创建SolrInputDocument
                SolrInputDocument inputDocument = new SolrInputDocument();
                inputDocument.setField("id",i.getId());
                inputDocument.setField("item_title",i.getTitle());
                inputDocument.setField("item_sell_point",i.getSell_point());
                inputDocument.setField("item_price",i.getPrice());
                inputDocument.setField("item_image",i.getImage());
                inputDocument.setField("item_category_name",i.getName());
                inputDocument.setField("item_desc",i.getItem_desc());
                //写入索引库
                solrTemplate.saveDocument(core,inputDocument);
            }
            solrTemplate.commit(core);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("数据导入失败");
    }

    @Override
    public List<SolrDocument> selectByQ(String q, Long page, Integer pageSize) {
        //设置高亮查询条件
        HighlightQuery query = new SimpleHighlightQuery();
        Criteria criteria = new Criteria("item_keywords");
        criteria.is(q);
        query.addCriteria(criteria);

        //设置高亮属性
        HighlightOptions options = new HighlightOptions();
        options.addField("item_title");//设置高亮显示的域
        options.setSimplePrefix("<em style='color:red'>");//设置高亮样式的前缀
        options.setSimplePostfix("</em>");
        query.setHighlightOptions(options);

        //分页
        query.setOffset((page-1)*pageSize);
        query.setRows(pageSize);

        //查询
        HighlightPage<SolrDocument> highlightPage = this.solrTemplate.queryForHighlightPage(this.core,query,SolrDocument.class);
        List<HighlightEntry<SolrDocument>> highlightEntries = highlightPage.getHighlighted();
        //System.out.println(highlightEntries);
        for (HighlightEntry<SolrDocument> h:highlightEntries) {
            SolrDocument entity = h.getEntity();//实体对象，是原始的实体对象
            List<HighlightEntry.Highlight>  highlights = h.getHighlights();
            //如果有高亮就取高亮
            if(highlights!=null && highlights.size()>0&&highlights.get(0).getSnipplets().size()>0){
                //System.out.println("1");
                //System.out.println(highlights.get(0).getSnipplets().get(0));
                entity.setItem_title(highlights.get(0).getSnipplets().get(0));
            }
        }
        List<SolrDocument> list = highlightPage.getContent();
        return list;
    }
}
