package org.lmmarise.vue.web.controller.controller;

import org.lmmarise.vue.domain.Author;
import org.lmmarise.vue.domain.Book;
import org.lmmarise.vue.web.controller.annotation.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/10 8:07 下午
 */
@ApiController("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    // @GetMapping // 对是否"/"不敏感
    @GetMapping("/")
    public String user(Model model) {
        HashMap<String, String> info = (HashMap<String, String>) model.getAttribute("info");
        model.addAllAttributes(info);
        return "user";
    }

    @GetMapping("/hello")
    public void hello(Model model) {
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Object value = map.get(key);
            log.info(key + ">>>>>" + value);
        }
    }

    /**
     * https://127.0.0.1:8081/app/user/book?b.name=《鸡你太美》&b.author=蔡徐坤&a.name=《大碗宽面》&a.author=6
     */
    @GetMapping("/book")
    @ResponseBody
    public String book(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        return book.toString() + "\r\n" + author.toString();
    }

    @RequestMapping("/info")
    @ResponseBody
    public String user() {
        return "{\n" +
                "    \"status\": \"0000\",\n" +
                "    \"message\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"title\": {\n" +
                "            \"id\": \"001\",\n" +
                "            \"name\" : \"白菜\"\n" +
                "        },\n" +
                "        \"content\": [\n" +
                "            {\n" +
                "                \"id\": \"001\",\n" +
                "                \"value\":\"你好 白菜\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"002\",\n" +
                "                 \"value\":\"你好 萝卜\" \n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }
}
