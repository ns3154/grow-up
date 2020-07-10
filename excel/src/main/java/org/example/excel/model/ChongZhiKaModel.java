package org.example.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import org.example.excel.utils.CustomStringStringConverter;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/07/10 19:59
 **/
public class ChongZhiKaModel {

    @ExcelProperty(value = "卡号", converter = CustomStringStringConverter.class)
    @ColumnWidth(25)
    private String cardNo;

    @ExcelProperty(value = "卡密", converter = CustomStringStringConverter.class)
    @ColumnWidth(30)
    private String password;

    @ExcelProperty(value = "金额", converter = CustomStringStringConverter.class)
    private String cash;

    @ExcelProperty(value = "批号", converter = CustomStringStringConverter.class)
    private String batchNum;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
}
