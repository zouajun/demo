package cn.zouajun.bzshop.backend.item.fallback;

import cn.zouajun.bzshop.backend.item.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.pojo.*;
import cn.zouajun.bzshop.utils.PageResult;
import feign.hystrix.FallbackFactory;

import java.util.List;

public class CommonItemFeignClientFallbackFactory implements FallbackFactory<CommonItemFeignClient> {
    @Override
    public CommonItemFeignClient create(Throwable throwable) {

        return new CommonItemFeignClient() {
            @Override
            public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
                return null;
            }

            @Override
            public Integer insertTbItem(TbItem tbItem) {
                return null;
            }

            @Override
            public Integer deleteItemById(TbItem tbItem) {
                return null;
            }

            @Override
            public TbItem findTbItemById(Long itemId) {
                return null;
            }

            @Override
            public Integer updateItem(TbItem tbItem) {
                return null;
            }

            @Override
            public List<TbItemCat> selectItemCategoryByParentId(Long id) {
                return null;
            }

            @Override
            public TbItemCat findTbItemCatByCid(Long cid) {
                return null;
            }

            @Override
            public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
                return null;
            }

            @Override
            public PageResult selectItemParamAll(Integer page, Integer rows) {
                return null;
            }

            @Override
            public Integer insertItemParam(TbItemParam itemParam) {
                return null;
            }

            @Override
            public Integer deleteItemParamById(Long id) {
                return null;
            }

            @Override
            public Integer insertItemDesc(TbItemDesc tbItemDesc) {
                return null;
            }

            @Override
            public TbItemDesc findTbItemDescById(Long itemId) {
                return null;
            }

            @Override
            public Integer updateItemDesc(TbItemDesc tbItemDesc) {
                return null;
            }

            @Override
            public TbItemParamItem findTbItemParamItemByItemId(Long itemId) {
                return null;
            }

            @Override
            public Integer updateItemParamItem(TbItemParamItem tbItemParamItem) {
                return null;
            }

            @Override
            public Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem) {
                return null;
            }
        };
    }
}
