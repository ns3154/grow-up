package com.example.resource;

import org.springframework.core.io.FileSystemResource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/09 14:46
 **/
public class ResourceMain {

    private static final String FILE_PATH = "C:\\temp\\gc.log";

    public static void main(String[] args) {
        FileSystemResource resource = new FileSystemResource(FILE_PATH);
        String path = resource.getPath();
        resource.createRelative("c");


    }
}
