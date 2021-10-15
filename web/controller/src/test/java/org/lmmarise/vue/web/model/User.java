package org.lmmarise.vue.web.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.lmmarise.vue.web.validation.ValidationGroup1;
import org.lmmarise.vue.web.validation.ValidationGroup2;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 4:17 下午
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Length
    private Integer id;
    @AssertFalse(groups = ValidationGroup2.class)
    private Boolean enabled;
    @PastOrPresent(groups = ValidationGroup2.class)
    private Date birthday;
    @Size(min = 5, max = 10, message = "{user.name.size}", groups = ValidationGroup1.class)
    private String name;
    @NotEmpty(message = "{user.address.notnull}", groups = ValidationGroup2.class)
    private String address;
    //    @DecimalMax(value = "200", message = "{user.age.size}")
    //    @Digits(integer = 3,fraction = 3,groups = ValidationGroup2.class)
    @Min(value = 1, message = "{user.age.size}", groups = ValidationGroup2.class)
    // @NegativeOrZero(groups = ValidationGroup2.class)
    private Integer age;
    @Email(message = "{user.email.pattern}")
    @NotNull(message = "{user.email.notnull}", groups = {ValidationGroup1.class, ValidationGroup2.class})
    private String email;
}
