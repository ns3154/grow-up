package com.example.demo.load;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/02/22 11:16
 **/
public class ClassLoadTest {

    public static void main(String[] args) {
    }

    public void druidTest () throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        //2.连接到数据"库"上去
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=GBK", "root", "");
    }
}
