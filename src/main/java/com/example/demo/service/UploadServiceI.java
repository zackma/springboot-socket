package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
public interface UploadServiceI {

    /**
     * 上传文件硬盘存储
     * @param files
     * @return
     */
    boolean transportUploadsToDisk(MultipartFile[] files);

    /**
     * 上传文件信息存表（根据具体业务表定保存信息字段）
     * @param files
     */
    void saveUploadsInfoIntoTable(MultipartFile[] files);
}
