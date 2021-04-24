package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_history")
public class LclOrderHistory implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "system_id")
    private Integer systemId;

    @TableField(value = "platform_id")
    private Integer platformId;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 审核状态
     */
    @TableField(value = "audit_status")
    private Integer auditStatus;

    /**
     * 配送状态
     */
    @TableField(value = "delivery_status")
    private Integer deliveryStatus;

    /**
     * 全局单号
     */
    @TableField(value = "global_order_no")
    private Long globalOrderNo;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 公司shop_id
     */
    @TableField(value = "company_id")
    private Integer companyId;

    /**
     * 发货站id
     */
    @TableField(value = "send_cargo_terminal_id")
    private Integer sendCargoTerminalId;

    /**
     * 收货站id
     */
    @TableField(value = "receive_cargo_terminal_id")
    private Integer receiveCargoTerminalId;

    /**
     * 路由id
     */
    @TableField(value = "routing_id")
    private Integer routingId;

    /**
     * 发货人省份
     */
    @TableField(value = "sender_province")
    private Integer senderProvince;

    /**
     * 发货人城市
     */
    @TableField(value = "sender_city")
    private Integer senderCity;

    /**
     * 发货人区/县
     */
    @TableField(value = "sender_county")
    private Integer senderCounty;

    /**
     * 发货人详细地址
     */
    @TableField(value = "sender_addr")
    private String senderAddr;

    /**
     * 发货人姓名
     */
    @TableField(value = "sender_name")
    private String senderName;

    /**
     * 发货人手机号
     */
    @TableField(value = "sender_phone")
    private String senderPhone;

    /**
     * 收货人省份
     */
    @TableField(value = "receiver_province")
    private Integer receiverProvince;

    /**
     * 收货人城市
     */
    @TableField(value = "receiver_city")
    private Integer receiverCity;

    /**
     * 收货人区/县
     */
    @TableField(value = "receiver_county")
    private Integer receiverCounty;

    /**
     * 收货人详细地址
     */
    @TableField(value = "receiver_addr")
    private String receiverAddr;

    /**
     * 收货人姓名
     */
    @TableField(value = "receiver_name")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @TableField(value = "receiver_phone")
    private String receiverPhone;

    /**
     * 运输异常类型
     */
    @TableField(value = "ex_type")
    private Byte exType;

    /**
     * 配送类型 1送货上门  2 自提 
     */
    @TableField(value = "delivery_type")
    private Byte deliveryType;

    /**
     * 配送员id
     */
    @TableField(value = "delivery_man_id")
    private Integer deliveryManId;

    /**
     * 发货备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(value = "create_user")
    private Long createUser;

    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 货站确认时间
     */
    @TableField(value = "confirm_time")
    private LocalDateTime confirmTime;

    /**
     * 首站配载时间
     */
    @TableField(value = "first_load_time")
    private LocalDateTime firstLoadTime;

    /**
     * 终点站到达时间
     */
    @TableField(value = "final_arrive_time")
    private LocalDateTime finalArriveTime;

    /**
     * 指派时间
     */
    @TableField(value = "assign_time")
    private LocalDateTime assignTime;

    /**
     * 配送员接单时间
     */
    @TableField(value = "accept_time")
    private LocalDateTime acceptTime;

    /**
     * 签收时间
     */
    @TableField(value = "receive_time")
    private LocalDateTime receiveTime;

    /**
     * 完成时间
     */
    @TableField(value = "finish_time")
    private LocalDateTime finishTime;

    /**
     * 取消时间
     */
    @TableField(value = "cancel_time")
    private LocalDateTime cancelTime;

    /**
     * 结算完成时间
     */
    @TableField(value = "settlement_time")
    private LocalDateTime settlementTime;

    /**
     * 预付款
     */
    @TableField(value = "pre_fee")
    private Long preFee;

    /**
     * 提付金额
     */
    @TableField(value = "sign_fee")
    private Long signFee;

    /**
     * 回付金额
     */
    @TableField(value = "back_fee")
    private Long backFee;

    /**
     * 月结
     */
    @TableField(value = "monthly_fee")
    private Long monthlyFee;

    /**
     * 货款
     */
    @TableField(value = "goods_fee")
    private Long goodsFee;

    /**
     * 使用代收货款，0.不使用; 1.使用
     */
    @TableField(value = "has_goods_fee")
    private Byte hasGoodsFee;

    /**
     * 货物总重
     */
    @TableField(value = "total_weight")
    private Long totalWeight;

    /**
     * 货物总体积
     */
    @TableField(value = "total_volume")
    private Long totalVolume;

    /**
     * 货物总数量
     */
    @TableField(value = "total_quantity")
    private Integer totalQuantity;

    /**
     * 总运费
     */
    @TableField(value = "total_freight")
    private Long totalFreight;

    /**
     * 删除标记：0未删除，1删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

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

    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 固定成本版本
     */
    @TableField(value = "fixed_fee_version")
    private Integer fixedFeeVersion;

    /**
     * 变动分润版本
     */
    @TableField(value = "change_fee_version")
    private Integer changeFeeVersion;

    private static final long serialVersionUID = 1L;
}