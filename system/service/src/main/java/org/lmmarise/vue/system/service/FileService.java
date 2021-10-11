package org.lmmarise.vue.system.service;

import org.lmmarise.vue.common.utils.ReqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 3:35 上午
 */
@Service
public class FileService {
    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    private final String multipartLocation;

    public FileService(@Value("${spring.servlet.multipart.location}") String multipartLocation) {
        this.multipartLocation = multipartLocation;
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            return new FileUrlResource(ReqUtil.pathAppend(multipartLocation, fileName));
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
