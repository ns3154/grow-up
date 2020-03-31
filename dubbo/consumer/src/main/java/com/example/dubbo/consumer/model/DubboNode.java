package com.example.dubbo.consumer.model;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/14 19:52
 **/
public class DubboNode {

    private String interFace;

    private String[] methods;

    private List<String> remoteHosts;

    public String getInterFace() {
        return interFace;
    }

    public void setInterFace(String interFace) {
        this.interFace = interFace;
    }

    public String[] getMethods() {
        return methods;
    }

    public void setMethods(String[] methods) {
        this.methods = methods;
    }

    public List<String> getRemoteHosts() {
        return remoteHosts;
    }

    public void setRemoteHosts(List<String> remoteHosts) {
        this.remoteHosts = remoteHosts;
    }
}
