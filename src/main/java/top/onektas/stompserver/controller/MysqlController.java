package top.onektas.stompserver.controller;

import lombok.SneakyThrows;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import top.onektas.stompserver.tools.ResultSetTranslator;

import java.sql.*;
import java.util.Properties;

/**
 * Mysql查询类
 *
 * @onektas
 */
public class MysqlController {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = MysqlController.db_url();

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/work?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // 数据库的用户名与密码以及SQL语句，在mysql.properties中修改
    private static String USER = MysqlController.user();
    private static String PASS = MysqlController.pass();
    private static String SQL = MysqlController.sql();

    @SneakyThrows
    private static String db_url() {
        new Properties();
        Properties props;
        props = PropertiesLoaderUtils.loadAllProperties("mysql.properties");
        String DB_URL = (String) props.get("DB_URL");

        return DB_URL;
    }

    @SneakyThrows
    private static String user() {
        new Properties();
        Properties props;
        props = PropertiesLoaderUtils.loadAllProperties("mysql.properties");
        String USER = (String) props.get("USER");

        return USER;
    }

    @SneakyThrows
    private static String pass() {
        new Properties();
        Properties props;
        props = PropertiesLoaderUtils.loadAllProperties("mysql.properties");
        String PASS = (String) props.get("PASS");

        return PASS;
    }

    @SneakyThrows
    private static String sql() {
        new Properties();
        Properties props;
        props = PropertiesLoaderUtils.loadAllProperties("mysql.properties");
        String SQL = (String) props.get("SQL");

        return SQL;
    }


    public String mysqlConntroller(String data) {
        Connection conn = null;
        Statement stmt = null;
        String result = null;

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println("查询数据库数据...");
            stmt = conn.createStatement();
            String sql;
            sql = SQL + data + "%'";
            ResultSet rs = stmt.executeQuery(sql);

            // 将查询结果转换为String
            result = new ResultSetTranslator().resultSetToJson(rs);

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return result;
    }
}
