package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:40 下午
 */
@Getter
@Setter
@Entity
public class Employeeec {
    @Id
    private Integer id;
    private Integer eid;
    private Date ecdate;
    private String ecreason;
    private Integer ecpoint;
    private Integer ectype;
    private String remark;

    public void setEcreason(String ecreason) {
        this.ecreason = ecreason == null ? null : ecreason.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
