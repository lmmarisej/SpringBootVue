package org.lmmarise.vue.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 2:51 下午
 */
@Getter
@Setter
@Entity
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createDate;
    private Boolean enabled;
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "position")
    private Employee employee;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
