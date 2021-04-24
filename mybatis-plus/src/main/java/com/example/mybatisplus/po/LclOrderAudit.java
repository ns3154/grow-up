package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_audit")
public class LclOrderAudit implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 审核状态 0:哨兵态无意义, 1:审核中,2:驳回,3:审核通过
     */
    @TableField(value = "audit_status")
    private Byte auditStatus;

    /**
     * 审核时间
     */
    @TableField(value = "audit_time")
    private LocalDateTime auditTime;

    /**
     * 异常类型, 1:退货,2:串货,3:修改运费,4:修改代收款
     */
    @TableField(value = "ex_type")
    private Byte exType;

    /**
     * 创建用户, 用来区分谁编辑的订单区分
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 每次审核，需更新version，保证同时只能一个人审核
     */
    @TableField(value = "version")
    private Integer version;

    /**
     * 订单历史关系表中的version
     */
    @TableField(value = "history_relation_version")
    private Integer historyRelationVersion;

    /**
     * 标记删除 0未删除, 1已删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建审核的货站id(也就是发起编辑的货站id)
     */
    @TableField(value = "create_cargo_terminal_id")
    private Integer createCargoTerminalId;

    private static final long serialVersionUID = 1L;
}