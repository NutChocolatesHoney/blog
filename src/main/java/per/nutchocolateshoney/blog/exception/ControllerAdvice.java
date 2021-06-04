package per.nutchocolateshoney.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.nutchocolateshoney.blog.common.Msg;
import per.nutchocolateshoney.blog.common.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author Zhenfeng Li
 * @version 1.0.0
 * @date 2020-02-19 16:47:31
 */
@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    /**
     * 全局异常处理
     *
     * @param exception 异常
     * @param request   HttpServletRequest
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception exception, HttpServletRequest request) {
        log.error("全局异常拦截，当前请求的uri：[{}],错误信息:[{}]", request.getRequestURI(), exception.getMessage());
        exception.printStackTrace();
        return Result.build(Msg.SYSTEM_FAIL, Msg.TEXT_SYSTEM_FAIL);
    }

    /**
     * 请求方式错误处理
     *
     * @param exception 异常
     * @param request   HttpServletRequest
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        log.error("全局异常拦截，方式错误，当前请求的uri：[{}],错误信息:[{}]", request.getRequestURI(), exception.getMessage());
        exception.printStackTrace();
        return Result.build(Msg.REQUEST_FAIL, Msg.TEXT_REQUEST_FAIL);
    }

    @ExceptionHandler(CommonException.class)
    Object handle(CommonException e) {
        log.error("异常拦截，方式错误，code：[{}], msg: [{}]", e.getCode(), e.getMsg());
        return Result.build(e.getCode(), e.getMsg());
    }
}
