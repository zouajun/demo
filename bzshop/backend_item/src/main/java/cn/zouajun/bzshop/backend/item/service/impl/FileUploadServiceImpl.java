package cn.zouajun.bzshop.backend.item.service.impl;

import cn.zouajun.bzshop.backend.item.service.FileUploadService;
import cn.zouajun.bzshop.utils.FtpUtil;
import cn.zouajun.bzshop.utils.IDUtils;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    /*FTP_HOST: 192.168.70.144
    FTP_PORT: 21
    FTP_USERNAME: ftpuser
    FTP_PASSWORD: ftpuser
    FTP_BASEPATH: /home/ftpuser/*/
    @Value("${FTP_HOST}")
    private String FTP_HOST;

    @Value("${FTP_PORT}")
    private int FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;
    @Override
    public Result fileUpload(MultipartFile file) {
        try {
            //定义上传图片的目录结构
            SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
            String path = sdf.format(new Date());

            //设置新的文件名
            String newFileName = IDUtils.genImageName() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            boolean f = FtpUtil.uploadFile(this.FTP_HOST, this.FTP_PORT, this.FTP_USERNAME, this.FTP_PASSWORD, this.FTP_BASEPATH, path, newFileName, file.getInputStream());
            String imageURL = "http://"+this.FTP_HOST+path+newFileName;
            return Result.ok(imageURL);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
