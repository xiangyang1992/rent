package com.keith.rent.web.test;

import com.alibaba.fastjson.JSONObject;
import com.keith.rent.web.weixinConfig.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class Test3 {

    private final static Logger logger = LoggerFactory.getLogger(Test3.class);

    private static String base_url = WeixinUtil.WEIXIN_ACCESS_TOKEN_URL;

    private static String corp_id = WeixinUtil.CORP_ID;

    private static String corp_secert = WeixinUtil.CORPSECRET;

    public static String  getAccessToken() throws Exception{
        String baseUrl = base_url + "corpid=" + corp_id + "&corpsecret=" + corp_secert;
        URL url = new URL(baseUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        return getReturn(connection);
    }

    private static String getReturn(HttpsURLConnection connection) throws Exception {
        StringBuffer sb = new StringBuffer();
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args)throws Exception {
        String token = Test3.getAccessToken();
        JSONObject object = null;
        object = JSONObject.parseObject(token);
        String access_token = object.getString("access_token");
        System.out.println(access_token);
    }
}
