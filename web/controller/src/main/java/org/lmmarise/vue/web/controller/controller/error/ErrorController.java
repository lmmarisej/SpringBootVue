package org.lmmarise.vue.web.controller.controller.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 访问一个不存在的url的时候，默认会创建error映射，并使用BasicErrorController中的@RequestMapping
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 3:00 下午
 */
@Controller
public class ErrorController extends BasicErrorController {

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
                           List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("custommsg", "出错啦！");
        return new ModelAndView("error/myErrorPage", model, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        body.put("custommsg", "出错啦！");
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }
}
