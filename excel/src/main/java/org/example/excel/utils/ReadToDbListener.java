package org.example.excel.utils;
import java.time.LocalDateTime;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.example.excel.domain.CouponPackageCode;
import org.example.excel.model.RenRen;
import org.example.excel.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 *      验证所有code是否是唯一
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2019/09/08 14:22
 **/
public class ReadToDbListener extends AnalysisEventListener<RenRen> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    List<RenRen> list = new ArrayList<>();


    @Override
    public void invoke(RenRen data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<CouponPackageCode> useList = new ArrayList<>(list.size());
        for (RenRen r : list) {
            CouponPackageCode cpc = new CouponPackageCode();
            cpc.setPackageId(3L);
            cpc.setCode(r.getCode());
            cpc.setRemark("新手优惠券");
            cpc.setAllowMultiExchange(0);
            cpc.setExchangedTimes(0L);
            cpc.setIsCard(0);
            cpc.setCreatorId(0L);
            cpc.setCreatorName("大雄");
            cpc.setCreatorMobile("15101010101");
            cpc.setCreateTime(LocalDateTime.now());
            cpc.setModifyTime(LocalDateTime.now());
            useList.add(cpc);
        }
        ExcelService bean = SpringContextUtil.getBean();
        int i = bean.create(useList);
        logger.info("要插入:{}, 实际插入:{}", list.size(), i);
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
