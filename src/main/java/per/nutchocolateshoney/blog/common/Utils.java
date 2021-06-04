package per.nutchocolateshoney.blog.common;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.management.modelmbean.XMLParseException;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author Zhenfeng Li
 * @date 2020-02-19 17:58:23
 */
public class Utils {
    /**
     * 返回一个定长的随机字符串(只包含数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        String allChar = "0123456789";
        for (int i = 0; i < length; i++) {
            builder.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return builder.toString();
    }

    /**
     * 获取真实Ip地址
     *
     * @param request 请求
     * @return 返回得到的真实Ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

    /**
     * 验证手机号
     *
     * @param phone 手机号
     * @return 验证通过返回<code>true</code>,失败<code>false</code>
     */
    public static Boolean phoneCheck(String phone) {
        String s2 = "^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$";
        // 验证手机号
        Pattern p = Pattern.compile(s2);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 验证邮箱
     *
     * @param phone 邮箱
     * @return 验证通过返回<code>true</code>,失败<code>false</code>
     */
    public static Boolean mailCheck(String phone) {
        String s2 = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        // 验证邮箱
        Pattern p = Pattern.compile(s2);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 获取MultipartFile的文件格式
     */
    public static String getMultipartFileType(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 微信签名MD5计算
     */
    public static String sign(Map<String, String> map, String key) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        builder.append("&key=").append(key);
        return DigestUtils.md5DigestAsHex(builder.substring(1).getBytes()).toUpperCase();
    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     */
    public static String mapToXml(Map<String, String> data) {
        StringBuilder builder = new StringBuilder("<xml>");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">");
        }
        return builder.append("</xml>").toString();
    }

    /**
     * xml转map
     *
     * @param xml XML格式的字符串
     * @return Map类型数据
     */
    public static Map<String, String> xmlToMap(String xml) throws XMLParseException {
        try {
            Map<String, String> map = new TreeMap<>();
            Document document = DocumentHelper.parseText(xml);
            Element nodeElement = document.getRootElement();
            List<?> node = nodeElement.elements();
            for (Object o : node) {
                Element elm = (Element) o;
                map.put(elm.getName(), elm.getText());
            }
            return map;
        } catch (Exception e) {
            throw new XMLParseException("XML解析异常");
        }
    }

    /**
     * 获取本机IP
     *
     * @return 返回本机Ip
     */
    public static String getLocalIp() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}
