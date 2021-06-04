package per.nutchocolateshoney.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 文章表
 *
 * @author WenQin Cai
 * @version 1.0.0
 * @date 2021-04-29 05:38:32
 */
@TableName("t_article")
@Accessors(chain = true)
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private Object id;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 文章缩略图
     */
    private String articleThumb;

    /**
     * 文章简介
     */
    private String articleIntro;

    /**
     * 文章内容
     */
    private String articleContent;
}