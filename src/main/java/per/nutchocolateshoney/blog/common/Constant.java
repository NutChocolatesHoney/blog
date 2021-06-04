package per.nutchocolateshoney.blog.common;

/**
 * 常量
 *
 * @author Zhenfeng Li
 * @date 2020-02-20 16:10:54
 */
public interface Constant {

    /**
     * 默认日期时间格式
     */
    String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * 图片验证吗前缀
     */
    String VERIFICATION_IMAGE = "VERIFICATION_IMAGE-";
    /**
     * 图片验证吗token前缀
     */
    String VERIFICATION_IMAGE_TOKEN = "VERIFICATION_IMAGE-TOKEN-";
    /**
     * 短信发送时间间隔前缀
     */
    String PHONE_C0DE_INTERVAL_PREFIX = "PHONE-C0DE-INTERVAL-PREFIX-";
    /**
     * redis中每天注册验证码发送次数限制前缀
     */
    String LIMIT_PREFIX = "LIMIT-";
    /**
     * redis员工超时自动离线前缀
     */
    String ON_LINE_PERSONNEL_PREFIX = "ON-LINE-PERSONNEL-";
    /**
     * 员工登录长时间无操作，自动离线时长（秒）2小时
     */
    Integer ON_LINE_TIME_PERSONNEL = 7200;
    /**
     * redis用户Token前缀
     */
    String USER_TOKEN_PREFIX = "USER-TOKEN-";
    /**
     * redis商户Token前缀
     */
    String MERCHANT_TOKEN_PREFIX = "MERCHANT-TOKEN-";
    /**
     * redis中注册验证码前缀
     */
    String CODE_PREFIX = "CODE-";
    /**
     * ip前缀
     */
    String IP_PREFIX = "IP-";
    /**
     * 手机号每日注册上限
     */
    Integer PHONE_LIMIT = 5;
    /**
     * 手机号验证码有效时长（秒）10分钟
     */
    Integer DURATION = 600;
    /**
     * session中员工的Key
     */
    String PERSONNEL_KEY = "PERSONNEL-KEY";
    /**
     * 用户权限Key
     */
    String PERSONNEL_AUTH_KEY = "PERSONNEL_AUTH_KEY-";
    /**
     * session中用户的Key
     */
    String USER_KEY = "USER-KEY";
}
