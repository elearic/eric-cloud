package net.eric.zz.consumer.utils;

import com.google.common.collect.Lists;
import org.ibase4j.core.support.security.BASE64Encoder;
import org.ibase4j.core.support.security.coder.*;
import org.ibase4j.core.util.Digests;
import org.ibase4j.core.util.Encodes;

import java.util.List;

/**
 * 数据加密辅助类(默认编码UTF-8)
 *
 * @author ShenHuaJie
 * @since 2011-12-31
 */
public final class SecurityUtil {
    public static final String CHARSET = "UTF-8";
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    /**
     * 默认算法密钥
     */
    private static final byte[] ENCRYPT_KEY = {-81, 0, 105, 7, -32, 26, -49, 88};
    private SecurityUtil() {
    }

    /**
     * BASE64解码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static final byte[] decryptBASE64(String key) {
        try {
            return new BASE64Encoder().decode(key);
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
    }

    /**
     * BASE64编码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static final String encryptBASE64(byte[] key) {
        try {
            return new BASE64Encoder().encode(key);
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }

    /**
     * 数据解密，算法（DES）
     *
     * @param cryptData 加密数据
     * @return 解密后的数据
     */
    public static final String decryptDes(String cryptData) {
        return decryptDes(cryptData, ENCRYPT_KEY);
    }

    /**
     * 数据加密，算法（DES）
     *
     * @param data 要进行加密的数据
     * @return 加密后的数据
     */
    public static final String encryptDes(String data) {
        return encryptDes(data, ENCRYPT_KEY);
    }

    /**
     * 基于MD5算法的单向加密
     *
     * @param strSrc 明文
     * @return 返回密文
     */
    public static final String encryptMd5(String strSrc) {
        String outString = null;
        try {
            outString = encryptBASE64(MDCoder.encodeMD5(strSrc.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return outString;
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static final String encryptSHA(String data) {
        try {
            return encryptBASE64(SHACoder.encodeSHA256(data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }

    /**
     * HMAC加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static final String encryptHMAC(String data) {
        return encryptHMAC(data, ENCRYPT_KEY);
    }

    /**
     * 数据解密，算法（DES）
     *
     * @param cryptData 加密数据
     * @return 解密后的数据
     */
    public static final String decryptDes(String cryptData, byte[] key) {
        String decryptedData = null;
        try {
            // 把字符串解码为字节数组，并解密
            decryptedData = new String(DESCoder.decrypt(decryptBASE64(cryptData), key));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }

    /**
     * 数据加密，算法（DES）
     *
     * @param data 要进行加密的数据
     * @return 加密后的数据
     */
    public static final String encryptDes(String data, byte[] key) {
        String encryptedData = null;
        try {
            // 加密，并把字节数组编码成字符串
            encryptedData = encryptBASE64(DESCoder.encrypt(data.getBytes(), key));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }

    /**
     * HMAC加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static final String encryptHMAC(String data, byte[] key) {
        try {
            return encryptBASE64(HmacCoder.encodeHmacSHA512(data.getBytes(CHARSET), key));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }

    /**
     * RSA签名
     *
     * @param data 原数据
     * @return
     */
    public static final String signRSA(String data, String privateKey) {
        try {
            return encryptBASE64(RSACoder.sign(data.getBytes(CHARSET), decryptBASE64(privateKey)));
        } catch (Exception e) {
            throw new RuntimeException("签名错误，错误信息：", e);
        }
    }

    /**
     * RSA验签
     *
     * @param data 原数据
     * @return
     */
    public static final boolean verifyRSA(String data, String publicKey, String sign) {
        try {
            return RSACoder.verify(data.getBytes(CHARSET), decryptBASE64(publicKey), decryptBASE64(sign));
        } catch (Exception e) {
            throw new RuntimeException("验签错误，错误信息：", e);
        }
    }

    /**
     * 数据加密，算法（RSA）
     *
     * @param data 数据
     * @return 加密后的数据
     */
    public static final String encryptRSAPrivate(String data, String privateKey) {
        try {
            return encryptBASE64(RSACoder.encryptByPrivateKey(data.getBytes(CHARSET), decryptBASE64(privateKey)));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
    }

    /**
     * 数据解密，算法（RSA）
     *
     * @param cryptData 加密数据
     * @return 解密后的数据
     */
    public static final String decryptRSAPublic(String cryptData, String publicKey) {
        try {
            // 把字符串解码为字节数组，并解密
            return new String(RSACoder.decryptByPublicKey(decryptBASE64(cryptData), decryptBASE64(publicKey)));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
    }

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    public static void main(String[] args) throws Exception {
//		System.out.println(encryptDes("114180"));
//		
        System.out.println(decryptDes("RLae5h5sCny="));
//		System.out.println(encryptMd5("SHJR"));
//		System.out.println(encryptSHA("1"));
//		Map<String, Object> key = RSACoder.initKey();

//	    String privateKeyPath = "G://private_key.pem";
//	    String publicKeyPath = "G://public_key.pem";
//	    
//		String privateKey = encryptBASE64(new PemFile(privateKeyPath).getPemObject().getContent());
//		String publicKey = encryptBASE64(new PemFile(publicKeyPath).getPemObject().getContent());
//		System.out.println("PI:" +privateKey);
//		System.out.println("PU:" +publicKey);
//		String sign = signRSA("132", privateKey);
//		System.out.println(sign);
//		String encrypt = encryptRSAPrivate("132", privateKey);
//		System.out.println(encrypt);
//		String org = decryptRSAPublic(encrypt, publicKey);
//		System.out.println(org);
//		System.out.println(verifyRSA(org, publicKey, sign));

        // System.out.println("-------列出加密服务提供者-----");
        // Provider[] pro = Security.getProviders();
        // for (Provider p : pro) {
        // System.out.println("Provider:" + p.getName() + " - version:" +
        // p.getVersion());
        // System.out.println(p.getInfo());
        // }
        // System.out.println("");
        // System.out.println("-------列出系统支持的消息摘要算法：");
        // for (String s : Security.getAlgorithms("MessageDigest")) {
        // System.out.println(s);
        // }
        // System.out.println("-------列出系统支持的生成公钥和私钥对的算法：");
        // for (String s : Security.getAlgorithms("KeyPairGenerator")) {
        // System.out.println(s);
        // }
//		System.out.println(entryptPassword("e10adc3949ba59abbe56e057f20f883e"));
//		System.out.println(validatePassword("e10adc3949ba59abbe56e057f20f883e", "670ba59715d41ea89181b75796bc8bea9f3abc498d0c62d93af508dd"));


        List<String> userIds = Lists.newArrayList(
                "894450",
                "894453",
                "894477",
                "894485",
                "894519",
                "894574",
                "894578",
                "894588",
                "894606",
                "894612",
                "894637",
                "894648",
                "894702",
                "894714",
                "894724",
                "894777",
                "894785",
                "894803",
                "894830",
                "894844",
                "894856",
                "894878",
                "894880",
                "894882",
                "894896",
                "894951",
                "894966",
                "894968",
                "895004",
                "895019",
                "895020",
                "895029",
                "895078",
                "895092",
                "895094",
                "895095",
                "895157",
                "895199",
                "895236",
                "895270",
                "895274",
                "895285",
                "895332",
                "895342",
                "895344",
                "895370",
                "895383",
                "895388",
                "895398",
                "895405",
                "895438",
                "895441",
                "895459",
                "895480",
                "895490",
                "895499",
                "895514",
                "895525",
                "895565",
                "895580",
                "895581",
                "895600",
                "895621",
                "895627",
                "895653",
                "895660",
                "895672",
                "895689",
                "895718",
                "895722",
                "895724",
                "895726",
                "895740",
                "895766",
                "895772",
                "895779",
                "895857",
                "895878",
                "895922",
                "895941",
                "895966",
                "895986",
                "896050",
                "896081",
                "896152",
                "896154",
                "896158",
                "896182",
                "896193",
                "896203",
                "896211",
                "896218",
                "896252",
                "896255",
                "896259",
                "896265",
                "896287",
                "896314",
                "896316",
                "896418",
                "1562633",
                "1562634",
                "1562665",
                "1562667",
                "1562671",
                "1562672",
                "1562688",
                "1562701",
                "1562704",
                "1562709",
                "1562723",
                "1562728",
                "1562732",
                "1562741",
                "1562746",
                "1562747",
                "1562749",
                "1562760",
                "1562771",
                "1562774",
                "1562780",
                "1562783",
                "1562787",
                "1562800",
                "1562801",
                "1562822",
                "1562854",
                "1562859",
                "1562864",
                "1562867",
                "1562869",
                "1562878",
                "1562893",
                "1562904",
                "1562917",
                "1562941",
                "1562942",
                "1562954",
                "1562982",
                "1562991",
                "1563004",
                "1563036",
                "1563055",
                "1563062",
                "1563069",
                "1563075",
                "1563084",
                "1563088",
                "1563093",
                "1563096",
                "1563098",
                "1563188",
                "1563204",
                "1563340",
                "1563346",
                "1563435",
                "1563486",
                "1563487",
                "1563498",
                "1563519",
                "1563547",
                "1563561",
                "1563591",
                "1563595",
                "1563598",
                "1563619",
                "1563632",
                "1563663",
                "1563693",
                "1563702",
                "1563712",
                "1563721",
                "1563751",
                "1563766",
                "1563788",
                "1563806",
                "1563817",
                "1563826",
                "1563834",
                "1563840",
                "1563853",
                "1563911",
                "1563961",
                "1563962",
                "1563984",
                "1564009",
                "1564043",
                "1564058",
                "1564217",
                "1564242",
                "1564252",
                "1564296",
                "1564300",
                "1564322",
                "1564338",
                "1564346",
                "1564359",
                "1564361",
                "1564364",
                "1564385"
        );

        for (String string : userIds) {
            System.out.println(encryptDes(string));
        }

    }


}
