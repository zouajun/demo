package cn.zouajun.bzshop.backend.item.service;

import cn.zouajun.bzshop.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    Result fileUpload(MultipartFile file);

}
