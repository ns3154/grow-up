package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_line_fee_history")
public class LclOrderLineFeeHistory implements Serializable {
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
     * 路线订单号
     */
    @TableField(value = "line_order_no")
    private Integer lineOrderNo;

    /**
     * 结算状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 路线id
     */
    @TableField(value = "line_id")
    private Integer lineId;

    /**
     * 线路版本号
     */
    @TableField(value = "line_version")
    private Integer lineVersion;

    /**
     * 发货站id
     */
    @TableField(value = "send_cargo_terminal_id")
    private Integer sendCargoTerminalId;

    /**
     * 发货站公司id
     */
    @TableField(value = "send_cargo_comp_id")
    private Integer sendCargoCompId;

    /**
     * 收货站id
     */
    @TableField(value = "receive_cargo_terminal_id")
    private Integer receiveCargoTerminalId;

    /**
     * 收货站公司id
     */
    @TableField(value = "receive_cargo_comp_id")
    private Integer receiveCargoCompId;

    /**
     * 线路总费用
     */
    @TableField(value = "line_total_fee")
    private Long lineTotalFee;

    /**
     * 公司运费
     */
    @TableField(value = "comp_fee")
    private Long compFee;

    /**
     * 分成比例
     */
    @TableField(value = "comp_percent")
    private BigDecimal compPercent;

    /**
     * 发货站分润
     */
    @TableField(value = "send_cargo_terminal_fee")
    private Long sendCargoTerminalFee;

    /**
     * 收货站分润
     */
    @TableField(value = "receive_cargo_terminal_fee")
    private Long receiveCargoTerminalFee;

    /**
     * 发货站分润百分比
     */
    @TableField(value = "send_cargo_terminal_percent")
    private BigDecimal sendCargoTerminalPercent;

    /**
     * 发货站类型，加盟或直营
     */
    @TableField(value = "send_cargo_terminal_type")
    private Byte sendCargoTerminalType;

    /**
     * 收货站分润百分比
     */
    @TableField(value = "receive_cargo_terminal_percent")
    private BigDecimal receiveCargoTerminalPercent;

    /**
     * 收货站类型，加盟或直营
     */
    @TableField(value = "receive_cargo_terminal_type")
    private Byte receiveCargoTerminalType;

    /**
     * 配载时间
     */
    @TableField(value = "load_time")
    private LocalDateTime loadTime;

    /**
     * 配载备注
     */
    @TableField(value = "load_marks")
    private String loadMarks;

    /**
     * 序号
     */
    @TableField(value = "seq")
    private Integer seq;

    /**
     * 是否删除，0未删除，1删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "create_user")
    private Long createUser;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version;

    private static final long serialVersionUID = 1L;
}