package com.example.utils;

/**
 * <pre>
 *     常量
 * </pre>
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2018/03/24 23:09
 **/
public interface Constants {

    enum ConstantsEnum {

        /****** 注释说明: 成功 *********************************/
        SUCCESS(200, "ok"),

        /******
         * 注释说明:
         * 入参不合法 例如: x < 10 , 客户端传 12 ,
         * 必要参数为传
         * *****************/
        PARAMETER_EXCEPTION(500, "入参不合法"),

        /****** 注释说明: 全局异常拦截时使用*********************************/
        SERVER_EXCEPTION_CODE(501, "服务器异常"),

        LOGIN_EXPIRES_CODE(502, "用户登录过期"),

        /****** 注释说明: 客户端同时两次请求 ********************/
        PROCESSING_CODE(503, "系统处理中"),

        CLOSURE_CODE(504, "该用户已被封禁"),

        /****** 注释说明: 待定 *********************************/
        ABNORMAL_OPERATION(505, "异常操作"),

        /****** 注释说明: 待定 ****************************/
        DATA_EXCEPTION(506, "数据异常,请联系管理员"),

        /****** 注释说明: ******************************/
        NO_PERMISSIONS(507, "权限不足"),

        /****** 注释说明:  客户端参数加密错误 ***************************/
        ENCRYPTION_ERROR(508, "加密错误");

        ConstantsEnum(int code, String message)
        {
            this.code = code;
            this.message = message;
        }

        private int code;

        private String message;

        public int getCode()
        {
            return code;
        }

        public String getMessage()
        {
            return message;
        }

    }

    /**
     * <pre>
     *     公共常量
     * </pre>
     * @author 杨帮东
     * @version 1.0
     * @date 2018/3/24 下午11:14
     */
    class Public {
        /****** 注释说明: 全局编码格式 *********************************/
        public static final String CHARSET = "UTF-8";

        /****** 注释说明: 使用中 *********************************/
        public static final int USERD = 1;

        /****** 注释说明: 未使用 *********************************/
        public static final Integer UN_USERD = 0;


        /****** 注释说明: 1.0 版本*********************************/
        public static final String VERSION_1 = "1.0";

    }

    /**
     * <pre>
     *     所有正则表达式
     * </pre>
     * @author 杨帮东
     * @version 1.0
     * @date 2018/3/24 下午11:14
     */
    class Regex {

        /****** 注释说明: 验证手机号 产品要求 十一位 存数字 即可 *********************************/
        public static final String REGEX_MOBILE_NUMBER = "^(13[0-9]|14[579]|15[012356789]|166|17[035678]|18[0-9]|19[89])[0-9]{8}$";
        public static final String REGEX_MOBILE = "^\\d{11}$";

        /****** 注释说明: 验证邮箱 *********************************/
        public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        /****** 注释说明: 身份证号 正则表达式*********************************/
        public static final String ID_NUMBER = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

        /****** 注释说明:允许数字字母 中文 空格 . - 不允许特殊字符开头(我的理解就是数字或者字母大小写或者中文开头都可以) *********************************/
        public static final String NICK_NAME = "^([a-zA-Z0-9\\u4e00-\\u9fa5])[a-zA-Z0-9\\u4e00-\\u9fa5\\s.-]{0,15}$";

        /**
         * 营业执照正则
         */
        public static final String UNIFORM_CREDIT_CODE = "^[0-9a-zA-Z]{0,35}$";
    }

    /**
     * websocket 专用常量
     */
    class WebSocket {
        /**
         * 登录类型
         */
        public static final int LOGIN_TYPE = 10000;
    }

    class RedisDbIndex {

        /****** 注释说明: 用户存放库 独占 不予其他共享********************/
        public static final int USER_TOKEN = 0;

        /****** 注释说明: 分布式锁所占库, 可共享 **************************/
        public static final int LOCK = 1;
    }
}
