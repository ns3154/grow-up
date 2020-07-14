package org.example.excel.utils;

import org.example.excel.model.ChongZhiKaModel;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    static String name = "20元充值卡-100张.xlsx";

    static String prePath = "";

    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            prePath =  "C:\\Users\\Ns\\Desktop\\充值卡\\";
        } else {
            prePath = "/Users/yang/Downloads/";
        }
    }

    static String outPath = prePath + name;

    @Test
    public void chognzhika() throws IOException {
        List<ChongZhiKaModel> list = new ArrayList<>();
        String batchnumber = yyyyMM.format(date);
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 100; i++) {
            String serialnum = decimalFormat.format(i);
            String cardno = cardyyyyMMdd + serialnum;
            ChongZhiKaModel czk = new ChongZhiKaModel();
            long password = SnowflakeUtil.getId();
            czk.setPassword(password + "");
            czk.setBatchNum(batchnumber);
            czk.setCardNo(cardno);
            czk.setCash(cash.toString());

            String sql = "INSERT INTO meboth_qiye.t_cards (cardno, password, cash, serialnum, batchnumber, createtime, "
                    + "isused) VALUES ('" + cardno + "', '" + password + "', " + cash + ", " + serialnum + ", '" + batchnumber + "', now(),  0);" + "\n";
            list.add(czk);
            sb.append(sql);

        }

        File file = new File(prePath + "sql.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(sb.toString());
        fw.close();

        ExcelUtils.create(name.split("\\.")[0], list , outPath, ChongZhiKaModel.class);
    }
}
