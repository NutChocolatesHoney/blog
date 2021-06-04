package per.nutchocolateshoney.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import per.nutchocolateshoney.blog.entity.Article;
import per.nutchocolateshoney.blog.mapper.ArticleMapper;
import per.nutchocolateshoney.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文章表ServiceImpl
 *
 * @author Zhenfeng Li
 * @version 0.0.1
 * @date 2020-04-01 08:04:05
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements  ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

}