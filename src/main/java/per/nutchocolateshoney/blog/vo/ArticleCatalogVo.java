package per.nutchocolateshoney.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Accessors(chain = true)
@Data
public class ArticleCatalogVo {

    private Integer level;

    private String title;

    private List<ArticleCatalogVo> catalogList;
}
