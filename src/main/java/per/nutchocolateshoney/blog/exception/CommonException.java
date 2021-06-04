package per.nutchocolateshoney.blog.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends Exception{
    /**
     * 状态码
     */
//    @ApiProperty(description = "状态码")
    private Integer code;

    /**
     * 响应消息
     */
//    @ApiProperty(description = "响应消息")
    private String msg;

    public CommonException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
