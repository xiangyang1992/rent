package com.keith.rent.web.weixinConfig;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public class WeixinUtil {
    //获取access_token URL
    public static  String WEIXIN_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?";

    public final static String CORP_ID = "wwc71c0d1b6902eca7";

    public final static String CORPSECRET = "AY2M-TUd8tHA7WpWZbrKPLwCvQFZPpMlYRPH6_E_tHw";

    public final static String WEIXIN_DEPT_USERS = "https://qyapi.weixin.qq.com/cgi-bin/user/list?";



    public static String  getAccessToken() throws Exception{
        String baseUrl = WEIXIN_ACCESS_TOKEN_URL + "corpid=" + CORP_ID + "&corpsecret=" + CORPSECRET;
        URL url = new URL(baseUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        String result = getReturn(connection);
        if (!StringUtils.isEmpty(result)) {
            JSONObject object = null;
            object = JSONObject.parseObject(result);
            return object.getString("access_token");
        }
        return null;
    }

    public static String getReturn(HttpsURLConnection connection) throws Exception {
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


    public static Object getAllDeptUser(String departmentId, String fetchChild)throws Exception {
        String url = WEIXIN_DEPT_USERS + "access_token=" + getAccessToken() + "&department_id=" + departmentId + "&fetch_child=" + fetchChild;
        URL newUrl = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) newUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        String result = getReturn(connection);
        JSONObject object = null;
        object = JSONObject.parseObject(result);
        if (object.get("errcode").equals(0)) {
            return object.get("userlist");
        }
        return null;
    }

}
