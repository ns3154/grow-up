package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_audit_sub")
public class LclOrderAuditSub implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 零担订单审核表主键id
     */
    @TableField(value = "lcl_order_audit_id")
    private Integer lclOrderAuditId;

    /**
     * 待审核货站id
     */
    @TableField(value = "audit_cargo_terminal_id")
    private Integer auditCargoTerminalId;

    /**
     * 审核状态 0:哨兵态无意义, 1:审核中,2:驳回,3:审核通过
     */
    @TableField(value = "audit_status")
    private Byte auditStatus;

    /**
     * 审核人
     */
    @TableField(value = "audit_user_id")
    private Long auditUserId;

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

    private static final long serialVersionUID = 1L;
}