package org.lmmarise.vue.web.controller;

import org.lmmarise.vue.web.model.User;
import org.lmmarise.vue.web.validation.ValidationGroup2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 4:21 下午
 */
@RestController("userVController")
@RequestMapping("/v")
public class UserVController {

    @PostMapping("/user")
    public List<String> addUser(@Validated(ValidationGroup2.class) // 本接口需要对组一进行验证
                                @RequestBody User user,
                                BindingResult result    // 对验证信息的封装
    ) {
        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                errors.add(error.getDefaultMessage());
            }
            return errors;
        }
        return Collections.singletonList(user.toString());
    }
}
