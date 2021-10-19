package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 12:38 下午
 */
@Getter
@Setter
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;
    private String idCard;
    private String wedlock;
    private String nativePlace;
    private Integer politicId;
    private String email;
    private String phone;
    private String address;
    private String engageForm;
    private String tiptopDegree;
    private String specialty;
    private String school;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginDate;
    private String workState;
    private String workId;
    private Double contractTerm;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date conversionTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date notWorkDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginContract;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date endContract;
    private Integer workAge;

    @ManyToOne
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobLevel jobLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Nation nation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Salary salary;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Position position;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Politicsstatus politicsstatus;
}
