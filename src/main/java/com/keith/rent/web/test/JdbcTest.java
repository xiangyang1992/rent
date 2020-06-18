package com.keith.rent.web.test;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */

@Slf4j
public class JdbcTest {


    private static  String driverClass = null;
    private static  String url = null;
    private static  String user = null;
    private static  String password = null;


    static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void init()throws Exception{
        InputStream in = JdbcTest.class.getResourceAsStream("/jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        driverClass = properties.getProperty("driverClass");
    }

    public Connection getConnection() throws Exception {
        Driver driver = (Driver) Class.forName(driverClass).newInstance();
        Properties info = new Properties();
        info.put("user", user);
        info.put("password", password);
        return driver.connect(url, info);
    }


    public void testDriverManager() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JdbcTest test = new JdbcTest();
        try {
            Connection connection = test.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        test.testDriverManager();
    }

}
