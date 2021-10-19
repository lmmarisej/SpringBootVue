package org.lmmarise.vue.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/16 2:38 下午
 */
@Getter
@Setter
@Embeddable
public class Meta implements Serializable {
    private Boolean keepAlive;
    private Boolean requireAuth;
}
