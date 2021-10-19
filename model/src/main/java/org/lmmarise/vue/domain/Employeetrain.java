package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:43 下午
 */
@Getter
@Setter
@Entity
public class Employeetrain {
    @Id
    private Integer id;
    private Integer eid;
    private Date traindate;
    private String traincontent;
    private String remark;

    public void setTraincontent(String traincontent) {
        this.traincontent = traincontent == null ? null : traincontent.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
