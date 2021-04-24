package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_history_relation")
public class LclOrderHistoryRelation implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 表名称
     */
    @TableField(value = "`table_name`")
    private String tableName;

    /**
     * 快照版本id(修改前原始数据的id),当此处id=0情况,比如,原始线路A-B,修改后线路A-B-C
     那么C就会对应此处的0
     */
    @TableField(value = "snapshot_ref_id")
    private Integer snapshotRefId;

    /**
     * 修改后数据引用id,此处也会出现0值,比如原始路线A-B-C,修改后A-B,那么原始数据对应的C就是0值
     */
    @TableField(value = "modify_ref_id")
    private Integer modifyRefId;

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

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}