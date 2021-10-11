package org.lmmarise.vue.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求处理工具
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/11 4:23 下午
 */
public class ReqUtil {
    /**
     * 返回请求的URL，包含到ContextPath截止
     * <p>
     * 注意：getContextPath一般返回的URL不以"/"结束
     */
    public static String getRequestContextUrl(HttpServletRequest req) {
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }

    /**
     * 路径拼接，将两个字符串中间加上"/"
     */
    public static String pathAppend(String pre, String sub) {
        return (pre + "/" + sub).replaceAll("//", "/");
    }
}
