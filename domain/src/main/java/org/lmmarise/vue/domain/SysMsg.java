package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 2:54 下午
 */
@Getter
@Setter
@Entity
public class SysMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer mid;
    private Integer type;
    private Integer hrid;
    private Integer state;
}
