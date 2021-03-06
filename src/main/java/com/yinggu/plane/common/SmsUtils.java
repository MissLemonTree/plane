package com.yinggu.plane.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName Sms
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/8 10:00
 * Version 1.0
 */
public class SmsUtils {


    public static String sendMessage(String phoneNum,String code){
        String userName = "wangyichen";
        String password = "12315ml";
        String content = "【测试】，登录验证码为:"+code+"。若非本人操作请忽略此消息。";
        String httpUrl = "http://api.smsbao.com/sms";
        StringBuffer httpArg = new StringBuffer();
        httpArg.append("u=").append(userName).append("&");
        httpArg.append("p=").append(md5(password)).append("&");
        httpArg.append("m=").append(phoneNum).append("&");
        httpArg.append("c=").append(encodeUrlString(content,"UTF-8"));

        return request(httpUrl,httpArg.toString());
    }


    public static String getCode(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 6; i++){
            int random = (int) (Math.random()*10);
            builder.append(random);
        }
        return builder.toString();
    }



    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = reader.readLine();
            if (strRead != null) {
                sbf.append(strRead);
                while ((strRead = reader.readLine()) != null) {
                    sbf.append("\n");
                    sbf.append(strRead);
                }
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    public static String encodeUrlString(String str, String charset) {
        String strret = null;
        if (str == null)
            return str;
        try {
            strret = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return strret;
    }
}
