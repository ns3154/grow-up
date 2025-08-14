package org.example.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.example.excel.model.RenRen;
import org.example.excel.service.ExcelService;
import org.example.excel.utils.ConvertCouponlUtil;
import org.example.excel.utils.ReadToDbListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 17:59
 **/
@RequestMapping("excel")
@RestController
public class ExcelController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ExcelService excelService;

    Set<String> set = new CopyOnWriteArraySet<>();

    private ThreadPoolExecutor threadPoolExecutor;

    @GetMapping("test")
    public void create() {
        threadPoolExecutor = new ThreadPoolExecutor(5, 30, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(200),
                new BasicThreadFactory.Builder()
                        .namingPattern(Joiner.on("-")
                                .join("create-db-", "%s"))
                        .build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        List<String> allFile = ConvertCouponlUtil.fileList();
        long start = System.currentTimeMillis();
        for (String s : allFile) {

            if (set.contains(s)) {
                logger.error("{}, 已插入,本次跳过", s);
                continue;
            }
            threadPoolExecutor.execute(() -> {
                logger.error("需要插入:{}", s);
                EasyExcel.read(s, RenRen.class, new ReadToDbListener()).sheet().doRead();
                set.add(s);
                logger.error("{} 执行完成", s);
            });
        }
        threadPoolExecutor.shutdown();

        for (;;) {
            if (threadPoolExecutor.isTerminated()) {
                logger.error("执行完毕.....时间:{}, 插入文件数:{}", System.currentTimeMillis() - start, set.size());
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
