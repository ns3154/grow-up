package com.example.converter.entity;

import com.example.model.bo.UserBO;
import com.example.model.dto.UserDTO;
import com.example.model.vo.UserVO;
import com.example.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * <pre>
 *      USER 实体转换器
 *      demo https://github.com/mapstruct/mapstruct-examples
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/08 16:45
 **/
@Mapper(componentModel = "spring", imports = {Constants.SexConverter.class})
public interface UserConverter {


    @Mappings({
            @Mapping(target = "userId", constant = "1234L"),
            @Mapping(target = "balance", constant = "12.3"),
//            @Mapping(target = "bd", constant = "12.32"),
//            @Mapping(target = "s", constant = ""),
//            @Mapping(target = "c", constant = "ss"),
            @Mapping(source = "name", target = "userName", defaultValue = "测试默认值"),
            @Mapping(target = "sex", expression = "java(SexConverter.getSexEnumByCode(dto.getSex()).getStr())"),
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm")
    })
    UserVO dtoToVo(UserDTO dto);


    @Mappings({
            @Mapping(target = "userId", constant = "1234L"),
            @Mapping(source = "dto.name", target = "userName", defaultValue = "测试默认值"),
            @Mapping(source = "dto.age", target = "age", defaultValue = "1"),
            @Mapping(target = "sex", expression = "java(SexConverter.getSexEnumByCode(dto.getSex()).getStr())"),
            @Mapping(source = "dto.createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "bo.balance",target = "balance", defaultValue = "23.1"),
            @Mapping(source = "bo.bd",target = "bdd", defaultValue = "23.2"),
            @Mapping(source = "bo.s",target = "ss", defaultValue = "1"),
            @Mapping(source = "bo.c",target = "ccc", defaultValue = "'a'"),
    })
    UserVO monyToOne(UserDTO dto, UserBO bo);

    List<UserVO> listDtoToVo(List<UserDTO> list);
}
