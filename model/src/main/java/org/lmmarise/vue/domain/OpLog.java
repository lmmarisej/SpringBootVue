package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 2:47 下午
 */
@Getter
@Setter
@Entity
public class OpLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date adddate;
    private String operate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Hr hr;

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }
}
