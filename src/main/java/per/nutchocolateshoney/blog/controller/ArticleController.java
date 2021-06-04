package per.nutchocolateshoney.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import per.nutchocolateshoney.blog.common.Result;
import per.nutchocolateshoney.blog.entity.Article;
import per.nutchocolateshoney.blog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

/**
* 文章表Controller
*
* @author WenQin Cai
* @version 1.0.0
* @date 2021-04-29 05:38:32
*/
@RestController
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 根据Id查询文章表
     */
    @GetMapping("/article/{id}")
    public Result<Article> content(@PathVariable Long id) {
        Article article = articleService.getById(id);
        return Result.buildQueryOk(article);
    }


    @PostMapping("/article/add")
    public Result<Boolean> add(@RequestBody String content) {
        return Result.buildSaveOk(true);
    }
}