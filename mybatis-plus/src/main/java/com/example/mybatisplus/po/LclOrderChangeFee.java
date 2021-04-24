package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_change_fee")
public class LclOrderChangeFee implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 路线订单号
     */
    @TableField(value = "line_order_no")
    private String lineOrderNo;

    /**
     * 全局单号
     */
    @TableField(value = "global_order_no")
    private Long globalOrderNo;

    /**
     * 费用类目key
     */
    @TableField(value = "category_key")
    private String categoryKey;

    /**
     * 费用value
     */
    @TableField(value = "category_val")
    private String categoryVal;

    /**
     * 费用
     */
    @TableField(value = "category_fee")
    private Long categoryFee;

    /**
     * 货站id
     */
    @TableField(value = "cargo_terminal_id")
    private Integer cargoTerminalId;

    /**
     * 货站类型：1，发货站，2 收货站，3 无（物流公司）
     */
    @TableField(value = "cargo_terminal_type")
    private Integer cargoTerminalType;

    /**
     * 删除标记：0未删除，1删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    @TableField(value = "version")
    private Integer version;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}