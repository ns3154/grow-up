package com.example.mvc.controller;

import com.example.mvc.config.TrackUtils;
import com.example.mvc.model.UserDTO;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


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

    public Controller() {
        TrackUtils.printTrack("controller....");
    }

    @PostMapping("post")
    public void postMethod (@RequestBody @Valid UserDTO dto) {
        log.info("入参: {}", dto);
    }

    @PostMapping("upload")
    public void upload(MultipartFile[] file) {
        log.info("入参: {}", file);
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
