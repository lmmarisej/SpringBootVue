package org.lmmarise.vue.web.controller.resolver;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义Error视图和数据
 *
 * @see org.springframework.boot.web.servlet.error.DefaultErrorAttributes 单独自定义数据
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 3:03 下午
 */
@Component
public class ErrorViewResolver implements org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView("error/errorPage");
        mv.addObject("custommsg", "出错啦！！");
        mv.addAllObjects(model);
        return mv;
    }
}
