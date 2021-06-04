package per.nutchocolateshoney.blog.common;

/**
 * 消息定义
 *
 * @author Zhenfeng Li
 * @version 1.0.0
 * @date 2019-09-19 09:02:33
 */
public interface Msg {
    /**
     * 成功返回
     */
    Integer OK = 200;
    String TEXT_SAVE_OK = "保存成功";
    String TEXT_UPDATE_OK = "修改成功";
    String TEXT_DELETE_OK = "删除成功";
    String TEXT_QUERY_OK = "查询成功";

    /**
     * 失败返回
     */
    Integer FAIL = 300;

    /**
     * token失效
     */
    Integer TOKEN_FAIL = 400;
    String TEXT_TOKEN_INVALID_FAIL = "请重新登录";

    /**
     * 权限不足
     */
    Integer AUTH_FAIL = 430;
    String TEXT_AUTH_FAIL = "权限不足";

    /**
     * 异常返回
     */
    Integer SYSTEM_FAIL = 500;
    String TEXT_SYSTEM_FAIL = "未知异常！~";
    Integer REQUEST_FAIL = 501;
    String TEXT_REQUEST_FAIL = "请求方式错误！~";

}
