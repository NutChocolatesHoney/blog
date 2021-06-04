package per.nutchocolateshoney.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import per.nutchocolateshoney.blog.entity.Article;

/**
 * 文章表Mapper
 *
 * @author WenQin Cai
 * @version 1.0.0
 * @date 2021-04-29 05:38:32
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}