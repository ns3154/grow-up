package org.example.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import org.example.excel.utils.CustomStringStringConverter;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 16:28
 **/
//@ContentRowHeight(20)
//@HeadRowHeight(20)
@ColumnWidth(16)
public class RenRen {

    /**
     * id
     */
    @ExcelProperty(value = "packageId", converter = CustomStringStringConverter.class)
    private String packageId;
    /**
     * 卡密
     */
    @ExcelProperty(value = "code", converter = CustomStringStringConverter.class)
    @ColumnWidth(30)
    private String code;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", converter = CustomStringStringConverter.class)
    private String createTime;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人", converter = CustomStringStringConverter.class)
    private String creator;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
