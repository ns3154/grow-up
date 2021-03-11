package com.example.data.binder.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;

/**
 * <pre>
 *      filter 放行路径
 *      目前在aplication.yaml中配置
 *      也支持阿波罗配置,如使用阿波罗配置 配置方法如下:
 *      token.filter.path.access = 1,2,3,4,5,6,7
 *      token.filter.path.noLogin = 1,2,3,4,5,6,7
 *      注:切记,一旦阿波罗配置了该项,无论阿波罗是否有数据,spring实现机制是 不再读取本地配置
 *      spring config 实现机制(读取配置的机制) :
 *             1.读取 application.yaml
 *             2.读取 application-*.yml (如果重复key,无论是否有数据,覆盖 1中参数)
 *             3.读取远程配置(此处阿波罗),如出现重复key,无论有没有数据 覆盖2配置,
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2019/10/31 14:56
 **/
@ConfigurationProperties(prefix = "")
public class FilterUrlProperties {

    /**
     * 不需要验证的URL
     */
    private HashSet<String> access;

    private HashSet<String> noLogin;

    public HashSet<String> getAccess() {
        return access;
    }

    public HashSet<String> getNoLogin() {
        return noLogin;
    }

    public void setAccess(HashSet<String> access) {
        this.access = access;
    }

    public void setNoLogin(HashSet<String> noLogin) {
        this.noLogin = noLogin;
    }
}
