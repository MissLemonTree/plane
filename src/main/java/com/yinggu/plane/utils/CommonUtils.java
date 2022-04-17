package com.yinggu.plane.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @ClassName CommonUtils
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/10 21:17
 * Version 1.0
 */
public class CommonUtils {
    public String getZFBinfoValue(String name) throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/zfbinfo.properties"));
        String filepath = properties.getProperty(name);
        return filepath;
    }

    public static String getUuid(){
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString();
        return uuidStr;
    }

}
