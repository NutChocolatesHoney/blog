package per.nutchocolateshoney.blog.common;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

/**
 * 微信退req_info款解密
 *
 * @author Zhenfeng Li
 * @version 1.0
 * @date 2020-12-21 12:16
 */
public class ParseReqInfo {
    /**
     * 解码器
     */
    private static Cipher cipher = null;

    /**
     * 解密
     */
    public static String parseReqInfo(String mchKey, String reqInfo) throws Exception {
        if (cipher == null) {
            init(mchKey);
        }
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] base64ByteArr = decoder.decode(reqInfo);
        return new String(cipher.doFinal(base64ByteArr));
    }

    public static void init(String mchKey) {
        //对key进行解密
        //商户秘钥
        String key = getMd5(mchKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Security.addProvider(new BouncyCastleProvider());
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static String getMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md5(str, md);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String md5(String strSrc, MessageDigest md) {
        byte[] bt = strSrc.getBytes();
        md.update(bt);
        return bytes2Hex(md.digest());
    }

    public static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }
}
 
