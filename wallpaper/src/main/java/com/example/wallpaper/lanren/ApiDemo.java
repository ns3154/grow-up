import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 调用示例
 *
 * @author chenZhiyong
 * @2018-3-14
 */
public class ApiDemo {

    // 合作方账号
    private static Long partnerId = 200323001810L;

    // 合作方加密秘钥
    private static String key = "M(z-hhV#e:KANS&k&wnU!NKJ";

    private static final String PARTNERID = "partnerId";

    private static final String APPID = "appId";

    private static final String TOKEN = "token";

    public static void main (String[] args) {
        // Demo 1 获取章节地址
        String url = "http://open.lrts.me/open/resource/entityPath";
        Map<String, String> params = new HashMap<>();
        params.put("bookId", 5622 + "");
        params.put("resId", 708261 + "");
        String response = httpGet(url, params);
        System.out.println(response);

        // Demo 2 下单（需要配置白名单）
        String orderurl = "http://moon-openapi.lrts.me/order/createOrder";
        Map<String, String> orderparams = new HashMap<>();
        orderparams.put("os", 2 + "");
        orderparams.put("phoneNum", "18825147553");
        orderparams.put("goodsType", 3 + "");
        orderparams.put("goodsNum", 6 + "");
        orderparams.put("totalFee", 5200 + "");
        orderparams.put("outOrderNum", "TSPLRT12345678");
        String response1 = httpPost(orderurl, orderparams);
        System.out.println(response1);
    }

    /**
     * 以get方式请求
     *
     * @param url    URL地址
     * @param params 请求参数
     * @return java.lang.String
     */
    public static String httpGet (String url, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        String path = path(url);
        String token = getToken(params, path, key);
        params.put("token", token);
        params.put("partnerId", partnerId + "");
        String paramString = getParamString(params);
        String link = url + "?" + paramString;
        // 可使用其他http请求工具类
        return doHttpGet(link);
    }

    /**
     * 以post方式请求
     *
     * @param url    URL地址
     * @param params 请求参数
     * @return java.lang.String
     */
    public static String httpPost (String url, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        String path = path(url);
        String token = getToken(params, path, key);
        params.put("token", token);
        params.put("partnerId", partnerId + "");
        String paramString = getParamString(params);
        // 可使用其他http请求工具类
        return doHttpPost(url, paramString);
    }

    /**
     * 获取路径
     *
     * @param url url
     * @return java.lang.String
     */
    private static String path (String url) {
        URL u;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return u.getPath();
    }

    /**
     * 获取token值
     *
     * @param paramMap 参数值
     * @param path     请求路径
     * @param signKey  签名key
     * @return java.lang.String
     */
    private static String getToken (Map<String, String> paramMap, String path, String signKey) {
        Set<String> paramNames = paramMap.keySet();
        List<String> availableParamNames = new ArrayList<>();
        for (String key : paramNames) {
            if (PARTNERID.equals(key) || TOKEN.equals(key) || APPID.equals(key)) {
                continue;
            }
            availableParamNames.add(key);
        }
        Collections.sort(availableParamNames);
        StringBuilder paramsStr = new StringBuilder(path);
        for (int i = 0; i < availableParamNames.size(); i++) {
            if (i == 0) {
                paramsStr.append("?");
            }
            String key = availableParamNames.get(i);
            paramsStr.append(key).append("=").append(paramMap.get(key));
            if (i != availableParamNames.size() - 1) {
                paramsStr.append("&");
            }
        }
        String signStr = paramsStr + signKey;
        return DigestUtils.md5Hex(signStr.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获取参数字符串
     *
     * @param params 参数map
     * @return java.lang.String
     */
    private static String getParamString (Map<String, String> params) {

        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value != null) {
                content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
            } else {
                content.append(i == 0 ? "" : "&").append(key).append("=");
            }

        }
        return content.toString();
    }

    private static String doHttpGet (String reqUrl) {
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            InputStream in;
            if (urlConn.getResponseCode() != 200) {
                in = urlConn.getErrorStream();
            } else {
                in = urlConn.getInputStream();
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int i; (i = bufferedInputStream.read(buf)) > 0; ) {
                out.write(buf, 0, i);
            }
            out.flush();
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static String doHttpPost (String reqUrl, String content) {
        HttpURLConnection urlConn;
        try {
            URL url = new URL(reqUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setConnectTimeout(50000);
            urlConn.setReadTimeout(50000);
            urlConn.setDoOutput(true);
            byte[] b = content.getBytes();
            urlConn.getOutputStream().write(b);
            urlConn.getOutputStream().flush();
            urlConn.getOutputStream().close();
            InputStream in;
            if (urlConn.getResponseCode() != 200) {
                in = urlConn.getErrorStream();
            } else {
                in = urlConn.getInputStream();
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String tempLine = rd.readLine();
            StringBuilder tempStr = new StringBuilder();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            rd.close();
            in.close();
            return tempStr.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}