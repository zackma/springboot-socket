package com.example.demo.service.impl;

import com.example.demo.service.UploadServiceI;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadServiceI {

    @Autowired
    private Environment evn;

    @Override
    public boolean transportUploadsToDisk(MultipartFile[] files) {
        boolean rst = true;
        //获取配置文件定义存储路径
        String uploadpathdisk = evn.getProperty("upload.path");
        //定义日期次目录
        Calendar cal = Calendar.getInstance();
        String day = cal.get(Calendar.DAY_OF_MONTH)>9?String.valueOf(cal.get(Calendar.DAY_OF_MONTH)):"0"+String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = cal.get(Calendar.MONTH) + 1 >9?String.valueOf(cal.get(Calendar.MONTH) + 1):"0"+String.valueOf(cal.get(Calendar.MONTH) + 1);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String subPath = year+month+day+"/";
        String filePath = uploadpathdisk+subPath;
        //新建file对象
        File dir=new File(filePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        for(MultipartFile mfile:files){
            if(!mfile.getContentType().contains("image")){//先判断上传文件是否为图片
                rst = false;
                return rst;
            }
            String fileName =new Date().getTime()+mfile.getOriginalFilename();
            File file = new File(filePath+fileName);
            try {
                //先尝试压缩写文件
                Thumbnails.of(mfile.getInputStream()).scale(1f).outputQuality(0.5f).toFile(file);
            } catch (IOException e) {
                try {
                    //压缩失败则用原始方法写文件
                    mfile.transferTo(file);
                } catch (IOException er) {
                    rst = false;
                    er.printStackTrace();
                }
            }
        }
        return rst;
    }

    @Override
    public void saveUploadsInfoIntoTable(MultipartFile[] files) {

    }
}
