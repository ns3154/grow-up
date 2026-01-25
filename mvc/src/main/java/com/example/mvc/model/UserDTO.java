package com.example.mvc.model;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/06 14:46
 **/
@Data
public class UserDTO implements Serializable {

    private Integer id;

    @NotEmpty
    private String name;




    public static void main(String[] args) {
        UserDTO dto = new UserDTO();
        dto.setId(0);
        dto.setName("");

    }

    public UserDTO id(Integer id) {
        this.id = id;
        return this;
    }

    public UserDTO name(String name) {
        this.name = name;
        return this;
    }
}
