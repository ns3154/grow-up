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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String suf = ".xlsx";

    private final String name = "电子兑换码-" + NUM + suf;

    private static final Integer NUM = 10000;

    private final String source = "C:\\Users\\Ns\\Desktop\\1w\\source\\" + name;

    private static final String USE = "C:\\Users\\Ns\\Desktop\\1w\\use";

    HashSet<Long> set = new HashSet<>(20000);

    ArrayList<Long> repeat = new ArrayList<>();

    private static final  String PACKAGE_ID = "4";

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
                .sheet("电子兑换码").doWrite(data());

        logger.error("生成:{}, 重复:{}", set.size(), repeat.size());
        logger.error("生成文件:{}", source);

    }

    public void check() {
        List<String> list = fileList();
        logger.error("文件数量:{}", list.size());
        for (String s : list) {
            logger.error("执行校验:{}", s);
            EasyExcel.read(s, RenRen.class, new ReadCheckListener()).sheet().doRead();
        }

    }

    private List data() {
        List<RenRen> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());
        for (int i = 0; i < NUM; i++) {
            Long id = SnowflakeUtil.getId();
            RenRen renren = new RenRen();
            renren.setPackageId(PACKAGE_ID);
            renren.setCode(id + "");
            renren.setCreateTime(dateStr);
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


    @Test
    public void test() {

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
