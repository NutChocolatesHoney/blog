package per.nutchocolateshoney.blog.configuation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import per.nutchocolateshoney.blog.common.Constant;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean??????
 *
 * @author Zhenfeng Li
 * @version 1.0
 * @date 2019-09-21 12:16
 */
@Configuration
public class BeanConfig {

    /**
     * redis ??????
     */
    @Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //JdkSerializationRedisSerializer?????????
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key??????String??????????????????
        template.setKeySerializer(stringRedisSerializer);
        // hash???key?????????String??????????????????
        template.setHashKeySerializer(stringRedisSerializer);
        Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //redis????????????????????????
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        format(objectMapper);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // value?????????????????????jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash???value?????????????????????jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //????????????????????????
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //????????????????????????
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        format(objectMapper);
        return objectMapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        messageConverter(restTemplate);
        return restTemplate;
    }

    /**
     * ???????????????
     */
    private void messageConverter(RestTemplate restTemplate) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_XML);
        mediaTypes.add(MediaType.APPLICATION_XML);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(StandardCharsets.UTF_8);
                break;
            }
        }
    }

    /**
     * ?????????
     *
     * @param objectMapper
     */
    private void format(ObjectMapper objectMapper) {
        SimpleModule simpleModule = new SimpleModule();
        //??????JSON???,?????????Long?????????String
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        //???????????????
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constant.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(Constant.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(Constant.DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Constant.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(Constant.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(Constant.DEFAULT_TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule()).registerModule(simpleModule);
    }

    /**
     * ????????????idea?????????????????????????????????lambda??????????????????
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = null;
                try {
                    date = LocalDateTime.parse(source, df);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, LocalDate> localDateConvert() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = null;
                try {
                    date = LocalDate.parse(source, df);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, LocalTime> localTimeConvert() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime date = null;
                try {
                    date = LocalTime.parse(source, df);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
}
