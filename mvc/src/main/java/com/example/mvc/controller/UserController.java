package com.example.mvc.controller;
import com.example.mvc.annotation.Secret;
import com.example.mvc.model.ModelMessge;
import com.example.mvc.model.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 16:03
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/")
    public String index(String s) {
        return s;
    }

    @PostMapping("advicePostTest")
    @Secret
    public ModelMessge<UserDTO> adviceTest(@RequestBody UserDTO userDTO) {
        return new ModelMessge<UserDTO>().ok(userDTO);
    }

    @GetMapping("adviceGetTest")
    @Secret
    public ModelMessge<UserDTO> adviceGetTest(UserDTO userDTO) {
        return new ModelMessge<UserDTO>().ok(userDTO);
    }

}
