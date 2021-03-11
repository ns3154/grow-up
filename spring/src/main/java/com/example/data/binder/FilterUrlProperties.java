package com.example.data.binder;

import com.example.annotation.ApolloRefreshScope;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;

/**
 * <pre>
 *      filter 放行路径
 *      目前在aplication.yaml中配置
 *      也支持阿波罗配置,如使用阿波罗配置 配置方法如下:
 *      token.filter.path.access = 1,2,3,4,5,6,7
 *      token.filter.path.noLogin = 1,2,3,4,5,6,7
 *      token.filter.path.ipLimits = 1,2,3,4
 *      配置优先级: 一旦阿波罗配置了该项,无论阿波罗是否有数据,spring实现机制是 不再读取本地配置
 *
 * @author 杨帮东
 * @since 1.0
 * @date 2019/10/31 14:56
 **/
@Component
@ConfigurationProperties(prefix = "token.filter.path")
@ApolloRefreshScope
public class FilterUrlProperties {

    /**
     * 不需要验证的URL
     */
    private HashSet<String> access;

    private HashSet<String> noLogin;


    private Map<String, String> idempotences;

    private HashSet<String> ipLimits;

    /**
     * 用户url请求限制
     */
    @Deprecated
    private HashSet<String> memberUrlLimits;

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

    public Map<String, String> getIdempotences() {
        return idempotences;
    }

    public void setIdempotences(Map<String, String> idempotences) {
        this.idempotences = idempotences;
    }

    public HashSet<String> getIpLimits() {
        return ipLimits;
    }

    public void setIpLimits(HashSet<String> ipLimits) {
        this.ipLimits = ipLimits;
    }

    @Deprecated
    public HashSet<String> getMemberUrlLimits() {
        return memberUrlLimits;
    }

    public void setMemberUrlLimits(HashSet<String> memberUrlLimits) {
        this.memberUrlLimits = memberUrlLimits;
    }
}
