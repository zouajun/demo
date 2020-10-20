package cn.zouajun.bzshop.backend.item.service;

import cn.zouajun.bzshop.utils.Result;
import org.springframework.stereotype.Service;

public interface ItemCategoryService {

    Result selectItemCategoryByParentId(Long id);
}
