package com.example.mvc.controller;

import com.example.converter.entity.UserConverter;
import com.example.model.ModelMessge;
import com.example.model.bo.UserBO;
import com.example.model.dto.UserDTO;
import com.example.model.vo.UserVO;
import com.example.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/08 16:54
 **/
@RestController
@RequestMapping("mapstruct")
public class MapstructController {

    @Resource
    private UserConverter userConverter;

    @GetMapping("dtoTovo")
    public ModelMessge<UserVO> dtoToVo(UserDTO dto) {
        dto.setCreateTime(new Date());
        UserVO userVO = userConverter.dtoToVo(dto);
        return new ModelMessge<UserVO>().ok(userVO);
    }

    @GetMapping("monyToOne")
    public ModelMessge<UserVO> monyToOne(UserDTO dto, UserBO bo) {
        UserVO vo = userConverter.monyToOne(dto, bo);
        return new ModelMessge<UserVO>().ok(vo);
    }

    @PostMapping("listDtoToVo")
    public ModelMessge<List<UserVO>> listDtoToVo() {
        List<UserDTO> list = new ArrayList<>();
        for (int i = 0;i < 10; i++) {
            UserDTO dto = new UserDTO();
            dto.setName("");
            dto.setAge(0);
            dto.setSex(0);
            dto.setCreateTime(new Date());
            list.add(dto);
        }
        List<UserVO> volist = userConverter.listDtoToVo(list);

        return new ModelMessge<List<UserVO>>().ok(volist);
    }

}
