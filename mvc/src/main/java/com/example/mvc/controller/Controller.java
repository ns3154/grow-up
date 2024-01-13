package com.example.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.example.mvc.config.MapConfiguration;
import com.example.mvc.config.TrackUtils;
import com.example.mvc.model.UserDTO;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/06 14:44
 **/
@RequestMapping("/")
@RestController
@Slf4j
public class Controller {

    @Resource
    private ConfigurableApplicationContext context;

    @Resource
    private MapConfiguration mapConfiguration;

    public Controller() {
        TrackUtils.printTrack("controller....");
    }

    @GetMapping("getMap")
    public Object getMapConfiguration() {
        return JSON.toJSONString(mapConfiguration);
    }

    @PostMapping("post")
    public UserDTO postMethod (@RequestBody @Valid UserDTO dto) {
        log.info("入参: {}", dto);
        return dto;
    }

    @PostMapping("upload")
    public void upload(MultipartFile[] file) {
        List<String> fileNames = new ArrayList<>();
        // 获取file文件的名称 ,并且将名称放入fileNames中,如果名称为空则跳过
        for (MultipartFile multipartFile : file) {
            String fileName = multipartFile.getOriginalFilename();
            if (StringUtils.isEmpty(fileName)) {
                continue;
            }
            fileNames.add(fileName);
        }

        // 逐行遍历 fileNames,并且输出到控制台
        for (String fileName : fileNames) {
            log.info("文件名: {}", fileName);
        }
    }

    @GetMapping("close")
    public void close() {
        context.close();;
    }

    @GetMapping("get")
    public String get () throws InterruptedException {
        Thread.sleep(100000L);
        return "get";
    }





}
