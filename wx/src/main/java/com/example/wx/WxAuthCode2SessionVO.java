package com.example.wx;

import java.io.Serializable;

/**
 * <pre>
 *   微信登录凭证返回体封装
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/28 14:37
 * @see
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html> 文档</a>
 **/
public class WxAuthCode2SessionVO implements Serializable {

    private static final long serialVersionUID = 4562208441475606272L;
    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，
     */
    private String unionid;

    /**
     * 错误码
     *  -1	     系统繁忙，此时请开发者稍候再试
     *  0	     请求成功
     * 40029	 code 无效
     * 45011	频率限制，每个用户每分钟100次
     */
    private Integer errcode;

    /**
     * 	错误信息
     */
    private String errmsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
