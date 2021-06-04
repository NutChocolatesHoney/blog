package per.nutchocolateshoney.blog.configuation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@Order(1)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CrosConfigProperties configProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //跨域支持
        http.cors();

        http
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http
                .exceptionHandling()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .httpBasic()
                .disable()
                .formLogin()
                .disable()
                .csrf()
                .disable()
                .logout()
                .disable();

    }

    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedOrigins(configProperties.allowedOrigins);
        configuration.setAllowedMethods(configProperties.allowedMethods);
        configuration.setAllowedHeaders(configProperties.allowedHeaders);
        if (!CollectionUtils.isEmpty(configProperties.exposedHeaders)) {
            configuration.setExposedHeaders(configProperties.exposedHeaders);
        }
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(configProperties.pathPattern, configuration);
        return new CorsFilter(source);
    }


}
