package org.lmmarise.vue.web.controller;

import org.lmmarise.vue.common.utils.ReqUtil;
import org.lmmarise.vue.web.bind.annotation.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.lmmarise.vue.common.utils.ReqUtil.getRequestContextUrl;


/**
 * 文件上传
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 12:42 上午
 */
@ApiController("/file")
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Value("${spring.servlet.multipart.location}")
    private String multipartLocation;   // 容器的上传文件存储绝对路径
    // @see org.lmmarise.vue.web.controller.UploadedFileController
    private final String UploadedFileControllerPath = "uploaded";   // 由于上传文件需要被访问，根据访问Controller再做一次偏移
    private String uploadPath;  // 通过计算得到最终上传文件存储位置（文件夹）

    @PostConstruct
    private void init() {
        mkDir(multipartLocation);
        uploadPath = ReqUtil.pathAppend(multipartLocation, UploadedFileControllerPath);
    }

    @GetMapping("/upload")
    public String upload() {
//        throw new MaxUploadSizeExceededException(200);    // Maximum upload size of 200 bytes exceeded
        return "upload";
    }

    @PostMapping("/upload")
    @CrossOrigin(maxAge = 1800, allowedHeaders = "*")
    @ResponseBody
    public String upload(MultipartFile uploadFile, HttpServletRequest req) {
        String format = sdf.format(new Date());
        File folder = mkDir(ReqUtil.pathAppend(uploadPath, format));
        String originalFilename = uploadFile.getOriginalFilename();
        String savedFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            uploadFile.transferTo(new File(folder, savedFileName));
        } catch (IOException e) {
            return "上传失败：" + e.getMessage();
        }
        return "上传成功：" + getRequestContextUrl(req) + "/" + UploadedFileControllerPath +
                "?path=" + UploadedFileControllerPath + "/" + format + "/" + savedFileName;
    }

    @GetMapping("/uploads")
    public String uploads() {
        return "uploads";
    }

    @PostMapping("/uploads")
    @ResponseBody
    public String uploads(MultipartFile[] uploadFiles, HttpServletRequest req) {
        String filePath = "";
        for (MultipartFile uploadFile : uploadFiles) {
            String format = sdf.format(new Date()); // 放入循环，避免12:00
            File folder = mkDir(ReqUtil.pathAppend(uploadPath, format));
            String originalFilename = uploadFile.getOriginalFilename();
            String savedFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            try {
                uploadFile.transferTo(new File(folder, savedFileName));
                filePath += getRequestContextUrl(req) + "/" + UploadedFileControllerPath +
                        "?path=" + UploadedFileControllerPath + "/" + format + "/" + savedFileName + "<br>";
            } catch (IOException e) {
                return "上传失败：" + e.getMessage();
            }
        }
        return "上传成功：" + filePath;
    }

    private File mkDir(String filePath) {
        File folder = new File(filePath);
        if (!folder.isDirectory()) {
            if (!folder.mkdirs()) {
                log.error("创建文件夹失败：" + filePath);
            }
            log.info("文件夹创建成功：{}", filePath);
        }
        return folder;
    }
}
