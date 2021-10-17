package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 2:44 下午
 */
@Getter
@Setter
@Entity
public class MsgContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String message;
    private Date createdate;

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
