package com.example.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName(value = "lcl_order_goods_history")
public class LclOrderGoodsHistory implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 货物名
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 规格型号
     */
    @TableField(value = "specification")
    private String specification;

    /**
     * 包装类型
     */
    @TableField(value = "package_type")
    private String packageType;

    /**
     * 单位重量
     */
    @TableField(value = "unit_weight")
    private Long unitWeight;

    /**
     * 单位体积
     */
    @TableField(value = "unit_volume")
    private Long unitVolume;

    /**
     * 数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 总重量 克
     */
    @TableField(value = "total_weight")
    private Long totalWeight;

    /**
     * 总体积 立方厘米
     */
    @TableField(value = "total_volume")
    private Long totalVolume;

    /**
     * 是否删除，0未删除，1删除
     */
    @TableField(value = "del_flag")
    private Byte delFlag;

    @TableField(value = "create_user")
    private Long createUser;

    @TableField(value = "update_user")
    private Long updateUser;

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