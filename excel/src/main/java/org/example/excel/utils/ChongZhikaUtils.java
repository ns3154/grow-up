package org.example.excel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.example.excel.model.ChongZhiKaModel;
import org.example.excel.model.RenRen;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/07/10 20:02
 */
public class ChongZhikaUtils {

    Date date = new Date();
    SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    String cardyyyyMMdd = yyyyMMdd.format(date);
    Double cash = 20.0d;
    String name = "20元充值卡-100张.xlsx";
    String source = "/Users/yang/Downloads/" + name;

    @Test
    public void chognzhika() {
        List<ChongZhiKaModel> list = new ArrayList<>();
        String batchnumber = yyyyMM.format(date);
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        for (int i = 0; i <= 100; i++) {
            String serialnum = decimalFormat.format(i);
            String cardno = cardyyyyMMdd + serialnum;
            ChongZhiKaModel czk = new ChongZhiKaModel();
            long password = SnowflakeUtil.getId();
            czk.setPassword(password + "");
            czk.setBatchNum(batchnumber);
            czk.setCardNo(cardno);
            czk.setCash(cash.toString());

            String s = "INSERT INTO meboth_qiye.t_cards (cardno, password, cash, serialnum, batchnumber, createtime, "
                    + "isused) VALUES ('" + cardno + "', '" + password + "', " + cash + ", " + serialnum + ", '" + batchnumber + "', now(),  0);";
            list.add(czk);
            System.out.println(s);
        }

        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle,
                contentWriteCellStyle);
        EasyExcel.write(source, ChongZhiKaModel.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("20" +
                "元充值卡").doWrite(list);
    }
}
