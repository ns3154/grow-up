package com.example.wallpaper.netbian;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/08/05 11:00
 **/
@Data
public class Base {

    private String uri;

    private String writeLocalPath;

    private String imgNameTxt;

    public Base (String uri, String writeLocalPath) {
        this.uri = uri;
        this.writeLocalPath = writeLocalPath;
        this.imgNameTxt = writeLocalPath + "names.txt";
    }
}
