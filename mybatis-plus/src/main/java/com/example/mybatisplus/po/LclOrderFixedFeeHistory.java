package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_fixed_fee_history")
public class LclOrderFixedFeeHistory implements Serializable {
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
     * 固定费用key
     */
    @TableField(value = "fee_key")
    private String feeKey;

    @TableField(value = "fee_val")
    private String feeVal;

    /**
     * 费用
     */
    @TableField(value = "fee")
    private Long fee;

    /**
     * 分配类型： 1 公司，2始发站，3终点站
     */
    @TableField(value = "`type`")
    private Byte type;

    /**
     * 是否固定 1 固定 2 不固定
     */
    @TableField(value = "is_fixed")
    private Byte isFixed;

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