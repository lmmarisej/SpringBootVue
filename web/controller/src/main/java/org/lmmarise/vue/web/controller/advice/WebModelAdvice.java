package org.lmmarise.vue.web.controller.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 1:59 下午
 */
@ControllerAdvice
public class WebModelAdvice {

    /**
     * 将info绑定到所有Controller自动注入的Model上
     */
    @ModelAttribute("info")
    public Map<String, String> userInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "罗贯中");
        map.put("gender", "男");
        return map;
    }

    /**
     * 请求参数预处理
     */
    @InitBinder("b")
    public void init(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");     // 将请求中 "b." 开头的参数解析为 @ModelAttribute("b")
    }

    @InitBinder("a")
    public void init2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }
}
