package org.example.excel.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <pre>
 *
 * 只支持单线程,代码也是单线程模式下开发,无任何同步操作.
 * 使用说明:
 * 1. {@link #BASE_PATH} 程序会自动创建根目录,请确保路径是文件夹
 *
 * 程序由来
 * -------------------------------------------------------------------------------------------------------------------
 * |如果user_profit_total表  没数据，执行下面1、2、3条sql，有数据只执行2、3
 * |   查询 select id, share_reward_total, user_id from user_profit_total
 * |            where user_id = (select user_id from users where mobile = '17610209982');
 * |
 * |    1. insert into `baojia_bike`.`user_profit_total` (`user_id`) values (5390504);
 * |
 * |    2.insert into `baojia_bike`.`adopt_balance_log` (`user_id`, `change_amount`, `current_amount`, `change_type`)
 * |         values (5390504, 176.9, (select share_reward_total from user_profit_total where user_id = 5390504), 21);
 * |
 * |    3.update user_profit_total set share_reward_total=share_reward_total + 176.9 where user_id = 5390504;
 * --------------------------------------------------------------------------------------------------------------------
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/03 17:39
 **/
public class MibiToJiangLiJIn {

    private static final Logger LOGGER = LoggerFactory.getLogger(MibiToJiangLiJIn.class);

    private static final String BASE_PATH = "C:\\Users\\Ns\\Desktop\\蜜币退款\\";
    /**
     * sql.txt 文件输出的目录
     */
    private static final String OUT_SQL_TXT = BASE_PATH + "sql.txt";

    /**
     * 数据校验不通过 或者问题数据
     */
    private static final String OUT_ERROR_TXT = BASE_PATH + "error.txt";

    /**
     * 非线程安全
     */
    private static final StringBuilder ERROR_STRING = new StringBuilder();

    /**
     * 读取的excel文件
     */
    private static final String IN_EXCEL = "C:\\Users\\Ns\\Desktop\\111.xlsx";

    private static final Pattern USERID_PATTERN = Pattern.compile("\\{USERID}");

    private static final Pattern NUMBERS_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$");

    private static final String MONEY = "{money}";

    private static final String USERID = "{USERID}";

    private static final Map<Integer, String> SQL = new HashMap<>();

    private static final JdbcTemplate JDBC_TEMPLATE;

    private static final String JDBC_URL = "jdbc:mysql://10.1.11.14:3310/baojia_bike?zeroDateTimeBehavior" +
            "=convertToNull" +
            "&useSSL=false" +
            "&serverTimezone=GMT%2B8";

    static {
        // 0  查询 user_profit_total表中是否有数据
        SQL.put(0,"select id, share_reward_total, user_id from user_profit_total where user_id = (select user_id from" +
                " users where mobile = ?)");
        // 1. 插入user_profit_total表数据
        SQL.put(1, "insert into `user_profit_total` (`user_id`) values ({USERID});");
        // 2. 插入 adopt_balance_log表数据
        SQL.put(2, "insert into `adopt_balance_log` (`user_id`, `change_amount`, `current_amount`, `change_type`) " +
                "values ({USERID}, {money}, " +
                "(select share_reward_total from user_profit_total where user_id = {USERID}), 21);");
        // 3. 更新 user_profit_total 表数据
        SQL.put(3, "update user_profit_total set share_reward_total=share_reward_total + {money} where user_id = " +
                "{USERID};");
        // 根据手机号查询USER_ID
        SQL.put(4, "select user_id from users where mobile = ? limit 1;");


        // 初始化数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername("readonly");
        dataSource.setPassword("m965soD6tLdnF3MF");
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        JDBC_TEMPLATE = new JdbcTemplate(dataSource);
        JDBC_TEMPLATE.afterPropertiesSet();

        File file = new File(BASE_PATH);
        boolean exists = file.exists();
        LOGGER.info("校验根目录是否存在:{}", exists);
        if (!exists) {
            boolean mkdir = file.mkdir();
            LOGGER.info("初始化........ 创建文件夹:{}", mkdir);
        }
    }

    public static void main(String[] args)  {
        List<ExcelModel> dataByExcel = getDateByExcel();
        List<QueryModel> userProfitTotals = getUserProfitTotals(dataByExcel);
        if (CollectionUtils.isEmpty(userProfitTotals)) {
            LOGGER.error("查询数据为空");
            return;
        }

        String sb = userProfitTotals
                .stream()
                .map(MibiToJiangLiJIn::createSql)
                .collect(Collectors.joining());
        File sqlFile = new File(OUT_SQL_TXT);
        try(FileWriter fw = new FileWriter(sqlFile, true)) {
            fw.write(sb);
        } catch (IOException e) {
            LOGGER.error("创建文件或写入时,发生IO错误", e);
        }

        File errorFile = new File(OUT_ERROR_TXT);
        try(FileWriter fw = new FileWriter(errorFile, true)) {
            fw.write(ERROR_STRING.toString());
        } catch (IOException e) {
            LOGGER.error("创建文件或写入时,发生IO错误", e);
        }

    }


    private static List<QueryModel> getUserProfitTotals(List<ExcelModel> dataByExcel) {
        List<QueryModel> list = new ArrayList<>(dataByExcel.size());
        dataByExcel.forEach((ExcelModel excelModel) -> {
            String mobile = excelModel.getMobile();
            List<QueryModel> query = userProfiTotalSql(mobile);
            list.add(getQueryModel(excelModel, query));
        });

        return list;
    }

    private static QueryModel getQueryModel(ExcelModel excelModel, List<QueryModel> query) {
        QueryModel queryModel = new QueryModel();
        if (!CollectionUtils.isEmpty(query)) {
            queryModel = query.get(0);
        } else {
            queryModel.setUserId(queryUserIdByMobile(excelModel.getMobile()));
        }
        queryModel.setExcelMoney(excelModel.getMoney());
        queryModel.setMobile(excelModel.getMobile());
        queryModel.setExcelId(excelModel.getId());
        return queryModel;
    }

    /**
     *
     * select id, share_reward_total, user_id from user_profit_total where
     * user_id = (select user_id from users where mobile = '13683304125');
     * @author 杨帮东
     * @param mobile
     * @since 1.0
     * @date 2020/11/3 21:07
     * @return java.util.List<org.example.excel.utils.MibiToJiangLiJIn.QueryModel>
     */
    private static List<QueryModel> userProfiTotalSql(String mobile) {
        Object[] qArgs = new Object[]{mobile};
        return JDBC_TEMPLATE.query(SQL.get(0), qArgs, new int[]{Types.VARCHAR}, (ResultSet rs, int rowNum) -> {
            QueryModel queryModel = new QueryModel();
            queryModel.setId(rs.getLong("id"));
            queryModel.setShareRewardTotal(rs.getDouble("share_reward_total"));
            queryModel.setUserId(rs.getLong("user_id"));
            return queryModel;
        });
    }

    private static Long queryUserIdByMobile(String mobile) {
        Object[] qArgs = new Object[]{mobile};
        return JDBC_TEMPLATE.query(SQL.get(4), qArgs, new int[]{Types.VARCHAR}, (ResultSet rs) -> {
                    long userId = -1L;
            while (rs.next()) {
                userId = rs.getLong(1);
            }
            return userId;
        });
    }

    private static String createSql(QueryModel queryModel) {
        StringBuilder sb = new StringBuilder();
        String mobile = queryModel.getMobile();
        String userId = queryModel.getUserId().toString();
        sb.append("#")
                .append("excel_Id:").append(queryModel.getExcelId()).append("  ")
                .append("mobile:").append(mobile).append("  ")
                .append("user_id:").append(userId).append("  ")
                .append("money:").append(queryModel.getExcelMoney()).append("\n");
        if (null == queryModel.getId()) {
            sb.append(SQL.get(1).replace(USERID, userId)).append("\n");
        }
        String sql2 = USERID_PATTERN.matcher(SQL.get(2)).replaceAll(userId)
                .replace(MONEY, queryModel.getExcelMoney());
        sb.append(sql2).append("\n");
        String sql3 = SQL.get(3).replace(USERID, userId).replace(MONEY, queryModel.getExcelMoney());
        sb.append(sql3);
        sb.append("\n").append("\n");
        return sb.toString();
    }

    /**
     * 从excel中获取数据
     * @author 杨帮东
     * @since 1.0
     * @date 2020/11/3 21:21
     * @return java.util.List<org.example.excel.utils.MibiToJiangLiJIn.ExcelModel>
     */
    private static List<ExcelModel> getDateByExcel() {
        ExcelListener listener = new ExcelListener();
        EasyExcel.read(IN_EXCEL, ExcelModel.class, listener).sheet().doRead();
        return listener.getList();
    }

    static class ExcelListener extends AnalysisEventListener<ExcelModel> {

        private final List<ExcelModel> excelList = new ArrayList<>();

        @Override
        public void invoke(ExcelModel data, AnalysisContext context) {
            if (NUMBERS_PATTERN.matcher(data.getMoney()).matches()) {
                excelList.add(data);
            } else {
                ERROR_STRING.append("#")
                        .append("excel_Id:").append(data.getId()).append("  ")
                        .append("mobile:").append(data.getMobile()).append("  ")
                        .append("money:").append(data.getMoney()).append("\n");
            }

        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
        }

        public List<ExcelModel> getList() {
            return Collections.unmodifiableList(excelList);
        }
    }

    static class QueryModel {

        /**
         * 赋值时 id 为空代表没查得到数据
         * 如果user_profit_total表 没数据，执行下面1、2、3条sql，有数据只执行2、3
         */
        private Long id;
        private Long userId;
        private Double shareRewardTotal;
        private String excelMoney;
        private String mobile;
        private String excelId;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Double getShareRewardTotal() {
            return shareRewardTotal;
        }

        public void setShareRewardTotal(Double shareRewardTotal) {
            this.shareRewardTotal = shareRewardTotal;
        }

        public String getExcelMoney() {
            return excelMoney;
        }

        public void setExcelMoney(String excelMoney) {
            this.excelMoney = excelMoney;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getExcelId() {
            return excelId;
        }

        public void setExcelId(String excelId) {
            this.excelId = excelId;
        }
    }

    public static class ExcelModel {

        @ExcelProperty(value = "id", index = 0, converter = CustomStringStringConverter.class)
        private String id;

        @ExcelProperty(value = "mobile", index = 1, converter = CustomStringStringConverter.class)
        private String mobile;

        @ExcelProperty(value = "money", index = 2)
        private String money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public static class CustomStringStringConverter implements Converter<String> {

        private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#");

        @Override
        public Class supportJavaTypeKey() {
            return String.class;
        }

        @Override
        public CellDataTypeEnum supportExcelTypeKey() {
            return CellDataTypeEnum.STRING;
        }

        /**
         * 这里读的时候会调用
         *
         * @param cellData
         *            NotNull
         * @param contentProperty
         *            Nullable
         * @param globalConfiguration
         *            NotNull
         * @return
         */
        @Override
        public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                        GlobalConfiguration globalConfiguration) {
            String value = cellData.getStringValue();
            if (StringUtils.isEmpty(value)) {
                value = DECIMAL_FORMAT.format(cellData.getDoubleValue());
            }

            return value;
        }

        /**
         * 这里是写的时候会调用 不用管
         *
         * @param value
         *            NotNull
         * @param contentProperty
         *            Nullable
         * @param globalConfiguration
         *            NotNull
         * @return
         */
        @Override
        public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                           GlobalConfiguration globalConfiguration) {
            return new CellData(value);
        }

    }
}
