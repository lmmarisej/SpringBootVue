package org.lmmarise.vue.common.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.data.domain.Page;

/**
 * 全局接口返回结果封装
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 11:29 上午
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private Boolean success;    // 本次操作是否成功
    private Integer code;       // 系统响应码
    private String msg;         // 返回消息
    private Object data;        // 响应数据

    public Result(Code code) {
        this.success = code.success;
        this.code = code.code;
        this.msg = code.msg;
    }

    public static Result of(Code code) {
        return new Result(code);
    }

    public static <T> Result ok(Page<T> page) {
        Result result = new Result(Code.SUCCESS);
        result.setData(page.getContent());
        return result;
    }

    public static Result ok(Object data) {
        Result result = new Result(Code.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result ok(String msg, Object data) {
        Result result = new Result(Code.SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail() {
        return new Result(Code.FAIL);
    }

    public static Result fail(Code code) {
        return new Result(code);
    }

    public static Result fail(String msg) {
        Result result = new Result(Code.FAIL);
        result.setMsg(msg);
        return result;
    }

    public static Result fail(Code code, String msg) {
        Result result = new Result(code);
        result.setMsg(msg);
        return result;
    }

    public static Result fail(Integer code, String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    /**
     * code 码为系统自定义的响应码，非与 HTTP 状态码对应。
     * <p>
     * 注意：状态码需要与前端一一对应，因此不建议直接在接口响应自定义的状态码，状态码自定义统一在此一一枚举。
     */
    public enum Code {
        SUCCESS(true, 200, "操作成功"),
        FAIL(false, 400, "操作失败"),
        UN_AUTHENTICATION(false, 401, "未认证"),
        UN_AUTHORIZATION(false, 403, "未授权"),
        SERVER_ERROR(false, 500, "系统繁忙");

        boolean success;
        int code;
        String msg;

        Code(boolean success, int code, String msg) {
            this.success = success;
            this.code = code;
            this.msg = msg;
        }
    }
}
