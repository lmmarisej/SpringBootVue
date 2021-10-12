package org.lmmarise.vue.common.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 分页查询条件封装
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/12 11:10 上午
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer page;   // 当前页
    Integer size;   // 页大小

    @Override
    public String toString() {
        return "PageQuery{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
