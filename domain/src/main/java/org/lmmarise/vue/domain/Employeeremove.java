package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:42 下午
 */
@Getter
@Setter
@Entity
public class Employeeremove {
    @Id
    private Integer id;
    private Integer eid;
    private Integer afterdepid;
    private Integer afterjobid;
    private Date removedate;
    private String reason;
    private String remark;

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
