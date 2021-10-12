package org.lmmarise.vue.web.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 1:14 下午
 */
@ControllerAdvice
public class WebExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(WebExceptionAdvice.class);

    @ExceptionHandler(MaxUploadSizeExceededException.class)     // 捕获全局指定异常
    public ModelAndView uploadException(MaxUploadSizeExceededException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "上传文件大小超出限制!" + e.getMessage());
        mv.setViewName("error/error");
        return mv;
    }

    /**
     * 捕获再抛给其它处理器处理
     * <p>
     * 禁止生吞大异常
     */
    @ExceptionHandler(Exception.class)
    public void exec(Exception e) throws Exception {
        log.info("exec    >>> " + e.getMessage());
        throw e;
    }
}
