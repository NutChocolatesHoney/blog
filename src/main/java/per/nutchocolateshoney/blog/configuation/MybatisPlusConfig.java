package per.nutchocolateshoney.blog.configuation;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import per.nutchocolateshoney.blog.common.SnowflakeIdWorker;

/**
 * 配置mybatis-plus
 *
 * @author Zhenfeng Li
 * @date 2020-01-07 15:15:11
 */
@Component
public class MybatisPlusConfig {
    private final SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        return plusProperties -> plusProperties.getGlobalConfig().setIdentifierGenerator((IdentifierGenerator) entity -> idWorker.nextId());
    }
}

