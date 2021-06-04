package per.nutchocolateshoney.blog.configuation;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Data
class CrosConfigProperties {

    String pathPattern;

    List<String> allowedOrigins;

    List<String> allowedHeaders;

    List<String> allowedMethods;

    List<String> exposedHeaders;

    CrosConfigProperties() {
        this.pathPattern = "/**";
        this.allowedOrigins = new ArrayList<>(Collections.singletonList("*"));
        this.allowedHeaders = new ArrayList<>(Collections.singletonList("*"));
        this.allowedMethods = new ArrayList<>(Collections.singletonList("*"));
    }
}