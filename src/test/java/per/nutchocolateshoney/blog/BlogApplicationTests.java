package per.nutchocolateshoney.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import per.nutchocolateshoney.blog.controller.ArticleController;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private ArticleController articleController;

    @Test
    void test() {
    }

}
