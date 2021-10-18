package org.lmmarise.vue.web.controller.controller.security;

import org.lmmarise.vue.common.utils.VerificationCode;
import org.lmmarise.vue.web.controller.annotation.ApiRestController;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/18 11:49 下午
 */
@ApiRestController
public class LoginController {

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image, resp.getOutputStream());
    }
}
