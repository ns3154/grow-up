package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_payment_history")
public class LclOrderPaymentHistory implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 全局单号
     */
    @TableField(value = "global_order_no")
    private Long globalOrderNo;

    /**
     * 支付订单号
     */
    @TableField(value = "pay_order_no")
    private Integer payOrderNo;

    /**
     * 支付流水号
     */
    @TableField(value = "pay_no")
    private Integer payNo;

    /**
     * 支付费用
     */
    @TableField(value = "payment_fee")
    private Long paymentFee;

    /**
     * 支付方式：1 现付，2 提付，3，回付，4 月结
     */
    @TableField(value = "payment_mode")
    private Integer paymentMode;

    /**
     * 支付途径：1 正常  2 预充值
     */
    @TableField(value = "payment_way")
    private Integer paymentWay;

    /**
     * 支付状态：0 待支付，1 已支付，2 支付失败
     */
    @TableField(value = "payment_status")
    private Integer paymentStatus;

    /**
     * 支付类型，1 线上  2 线下
     */
    @TableField(value = "payment_type")
    private Integer paymentType;

    @TableField(value = "shop_id")
    private Integer shopId;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private LocalDateTime payTime;

    /**
     * 删除标志位 0未删除，1删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}