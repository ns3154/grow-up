package org.example.excel.domain;

import java.time.LocalDateTime;

/**
    * 券包生成的兑换码表
    */
public class CouponPackageCode {
    private Long id;

    /**
    * 券包主键
    */
    private Long packageId;

    /**
    * 券码
    */
    private String code;

    /**
    * 备注
    */
    private String remark;

    /**
    * 是否允许兑换多次 0 不 1行
    */
    private Integer allowMultiExchange;

    /**
    * 已被兑换次数
    */
    private Long exchangedTimes;

    /**
    * 0:券1卡2都
    */
    private Integer isCard;

    /**
    * 后台创建人id
    */
    private Long creatorId;

    /**
    * 后台创建人姓名
    */
    private String creatorName;

    /**
    * 后台创建人手机号
    */
    private String creatorMobile;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAllowMultiExchange() {
        return allowMultiExchange;
    }

    public void setAllowMultiExchange(Integer allowMultiExchange) {
        this.allowMultiExchange = allowMultiExchange;
    }

    public Long getExchangedTimes() {
        return exchangedTimes;
    }

    public void setExchangedTimes(Long exchangedTimes) {
        this.exchangedTimes = exchangedTimes;
    }

    public Integer getIsCard() {
        return isCard;
    }

    public void setIsCard(Integer isCard) {
        this.isCard = isCard;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorMobile() {
        return creatorMobile;
    }

    public void setCreatorMobile(String creatorMobile) {
        this.creatorMobile = creatorMobile;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}