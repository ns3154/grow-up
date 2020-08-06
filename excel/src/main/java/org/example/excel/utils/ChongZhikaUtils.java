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
 *      样例数据
 *      INSERT INTO meboth_qiye.t_cards (cardno, password, cash, serialnum, batchnumber, createtime, isused)
 *      VALUES ('202008060000', '1291287919981494272', 100.0, 0000, '202008', now(),  0);
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



    /*************************** 必填参数 ****************************/
    // 金额
    static final Double CASH = 100.0d;
    // 充值卡数量
    static final int CREATE_NUMS = 32;
    static final String EXCEL_NAME = "100元充值卡-1张.xlsx";
    static final String SQL_NAME = "100元sql.txt";
    /*************************** 必填参数 ****************************/

    /**
     * 默认初始值为0
     * 设置条件,1天之内生成超过多个批次数据时修改
     */
    static int serialnum = 0;

    static String prePath = "";

    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            prePath =  "C:\\Users\\Ns\\Desktop\\充值卡\\";
        } else {
            prePath = "/Users/yang/Downloads/";
        }
    }

    static String outPath = prePath + EXCEL_NAME;

    @Test
    public void chognzhika() throws IOException {
        List<ChongZhiKaModel> list = new ArrayList<>();
        String batchnumber = yyyyMM.format(date);
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CREATE_NUMS; i++) {
            String serialnumStr = decimalFormat.format(serialnum);
            String cardno = cardyyyyMMdd + serialnumStr;
            ChongZhiKaModel czk = new ChongZhiKaModel();
            long password = SnowflakeUtil.getId();
            czk.setPassword(password + "");
            czk.setBatchNum(batchnumber);
            czk.setCardNo(cardno);
            czk.setCash(CASH.toString());

            String sql = "INSERT INTO meboth_qiye.t_cards (cardno, password, cash, serialnum, batchnumber, createtime, "
                    + "isused) VALUES ('" + cardno + "', '" + password + "', " + CASH + ", " + serialnum + ", '" + batchnumber + "', now(),  0);" + "\n";
            list.add(czk);
            sb.append(sql);
            serialnum++;
        }

        File file = new File(prePath + SQL_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(sb.toString());
        fw.close();

        ExcelUtils.create(EXCEL_NAME.split("\\.")[0], list , outPath, ChongZhiKaModel.class);
    }
}
