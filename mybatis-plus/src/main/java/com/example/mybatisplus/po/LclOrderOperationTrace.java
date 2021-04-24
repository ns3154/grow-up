package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_operation_trace")
public class LclOrderOperationTrace implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 操作完后的订单版本
     */
    @TableField(value = "relation_version")
    private Integer relationVersion;

    @TableField(value = "cargo_terminal_id")
    private Integer cargoTerminalId;

    /**
     * 操作类型
     */
    @TableField(value = "operation_type")
    private Integer operationType;

    /**
     * 操作类型名
     */
    @TableField(value = "operation_name")
    private String operationName;

    /**
     * 备注，只有在添加备注的时候才会有值
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 备注图片url，只有在添加备注的时候才会有值，如果有多个用,拼接。
     */
    @TableField(value = "url")
    private String url;

    /**
     * 删除标记：0未删除，1删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}