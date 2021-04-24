package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_agent_fee")
public class LclOrderAgentFee implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 全局订单号
     */
    @TableField(value = "global_order_no")
    private Long globalOrderNo;

    /**
     * 支付订单号，和支付信息表中支付提付支付单号共用一个
     */
    @TableField(value = "pay_order_no")
    private Integer payOrderNo;

    /**
     * 总费用
     */
    @TableField(value = "total_fee")
    private Long totalFee;

    /**
     * 顾客费用
     */
    @TableField(value = "customer_fee")
    private Long customerFee;

    /**
     * 服务费
     */
    @TableField(value = "service_fee")
    private Long serviceFee;

    @TableField(value = "agent_fee_version")
    private Integer agentFeeVersion;

    /**
     * 代收账户
     */
    @TableField(value = "agent_account")
    private String agentAccount;

    /**
     * 身份证号
     */
    @TableField(value = "agent_identity")
    private String agentIdentity;

    /**
     * 代收银行
     */
    @TableField(value = "agent_bank")
    private String agentBank;

    /**
     * 代收账号
     */
    @TableField(value = "agent_card_no")
    private String agentCardNo;

    /**
     * 手机号
     */
    @TableField(value = "phone_num")
    private String phoneNum;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 删除标记：0未删除，1删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}