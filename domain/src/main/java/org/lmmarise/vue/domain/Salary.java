package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 2:53 下午
 */
@Getter
@Setter
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer basicSalary;
    private Integer bonus;
    private Integer lunchSalary;
    private Integer trafficSalary;
    private Integer allSalary;
    private Integer pensionBase;
    private Float pensionPer;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Integer medicalBase;
    private Float medicalPer;
    private Integer accumulationFundBase;
    private Float accumulationFundPer;
    private String name;

    @OneToOne(mappedBy = "salary")
    private Employee employee;  // 在当前实体表自动生成表字段 Integer employeeId;
}
