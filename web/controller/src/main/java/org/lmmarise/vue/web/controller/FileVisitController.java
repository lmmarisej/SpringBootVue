package org.lmmarise.vue.web.controller;

import org.lmmarise.vue.system.service.FileService;
import org.lmmarise.vue.web.bind.annotation.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 对上传成功的文件对外提供访问
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 2:27 上午
 */
@ApiController("/uploaded")
public class FileVisitController {
    private static final Logger log = LoggerFactory.getLogger(FileVisitController.class);

    @javax.annotation.Resource
    private FileService fileService;

    @RequestMapping
    public ResponseEntity<Resource> downloadFile(String path, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(path);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";   // Fallback to the default content type if type could not be determined
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
