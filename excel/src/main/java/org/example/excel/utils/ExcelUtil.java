package org.example.excel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.example.excel.model.RenRen;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <pre>
 *      城市+品牌+数量
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 16:12
 **/
public class ExcelUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String suf = ".xlsx";

    private final String name = "汉源-人民4系-" + NUM + suf;

    private static final Integer NUM = 9500;

    private final String source = "C:\\Users\\Ns\\Desktop\\90w\\source\\" + name;

    private static final String USE = "C:\\Users\\Ns\\Desktop\\90w\\use";

    HashSet<Long> set = new HashSet<>(10000);

    ArrayList<Long> repeat = new ArrayList<>();

    @Test
    public void create() {
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
        EasyExcel.write(source, RenRen.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet("新手优惠券券包兑换码").doWrite(data());

        logger.info("生成:{}, 重复:{}", set.size(), repeat.size());
        logger.info("生成文件:{}", source);

    }

    public void check() {
        List<String> list = fileList();
        logger.info("文件数量:{}", list.size());
//        for (String s : list) {
//            logger.info("执行校验:{}", s);
//            EasyExcel.read(s, RenRen.class, new ReadListener()).sheet().doRead();
//        }
        EasyExcel.read(list.get(0), RenRen.class, new ReadCheckListener()).sheet().doRead();

    }

    private List data() {
        List<RenRen> list = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            Long id = SnowflakeUtil.getId();
            RenRen renren = new RenRen();
            renren.setPackageId("3");
            renren.setCode(id + "");
            renren.setCreateTime("2020-04-21");
            renren.setCreator("大雄");
            list.add(renren);
            if (set.contains(id)) {
                repeat.add(id);
            } else {
                set.add(id);
            }
        }
        return list;
    }




    public static List<String> fileList() {
        List<String> list = new ArrayList<>(100);
        File file = new File(USE);
        File[] files = file.listFiles();
        for (File f : files) {
            list.add(f.getPath());
        }
        return list;
    }
}
