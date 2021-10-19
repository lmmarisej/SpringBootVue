package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 2:32 下午
 */
@Getter
@Setter
@Entity
public class MailSendLog extends AuditModel {
    private String msgId;
    private Integer empId;
    // 0 消息投递中   1 投递成功   2 投递失败
    private Integer status;
    private String routeKey;
    private String exchange;
    private Integer count;
    private Date tryTime;
}
