package org.example.excel.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.example.excel.model.RenRen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * <pre>
 *      验证所有code是否是唯一
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2019/09/08 14:22
 **/
public class ReadCheckListener extends AnalysisEventListener<RenRen> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    List<RenRen> list = new ArrayList<>();

    Set<Long> set = new HashSet<>();

    @Override
    public void invoke(RenRen data, AnalysisContext context) {
        Long id = Long.parseLong(data.getCode());
        if (set.contains(id)) {
            logger.error("code冲突:{}", id);
        }
        set.add(id);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        System.out.println("在转换异常");
    }

}
