package com.example.demo.load;

import org.junit.Test;
import sun.reflect.Reflection;

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
        System.out.println(A.sss.sssst);
    }

    @Test
    public void druidTest () throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        //2.连接到数据"库"上去
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=GBK", "root", "");
    }
}
