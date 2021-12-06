package com.example.mvc.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/06 14:46
 **/
@Data
public class UserDTO {

    private Integer id;

    @NotEmpty
    private String name;

}
