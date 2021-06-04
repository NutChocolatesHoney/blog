package per.nutchocolateshoney.blog.configuation;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.ServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 重写参数绑定器
 *
 * @author Zhenfeng Li
 * @version 1.0.0
 * @date 2019-09-26 09:12:45
 */
public class CustomServletRequestDataBinder extends ServletRequestDataBinder {

    private final Pattern underLinePattern = Pattern.compile("_(\\w)");

    public CustomServletRequestDataBinder(final Object target) {
        super(target);
    }

    /**
     * 遍历请求参数对象 把请求参数的名转换成驼峰体
     * 重写addBindValues绑定数值的方法
     *
     * @param propertyValues 请求参数列表
     * @param request        请求
     */
    @Override
    protected void addBindValues(MutablePropertyValues propertyValues, ServletRequest request) {
        List<PropertyValue> pvs = propertyValues.getPropertyValueList();
        List<PropertyValue> adds = new LinkedList<>();
        for (PropertyValue pv : pvs) {
            String name = pv.getName();
            String camel = this.underLineToCamel(name);
            if (!name.equals(camel)) {
                adds.add(new PropertyValue(camel, pv.getValue()));
            }
        }
        pvs.addAll(adds);
    }

    /**
     * 把app_id转换成appId
     *
     * @param value 要转换的下划线字符串
     * @return 驼峰体字符串
     */
    private String underLineToCamel(final String value) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = underLinePattern.matcher(value);
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();

    }
}
