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
 * @since 2021/10/16 12:32 下午
 */
@Getter
@Setter
@Entity
public class Appraise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer eid;
    private Date appdate;
    private String appresult;
    private String appcontent;
    private String remark;
}
