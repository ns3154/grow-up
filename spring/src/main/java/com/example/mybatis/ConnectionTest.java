package com.example.mybatis;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/30 18:53
 **/
public class ConnectionTest {

    private final static Logger logger = LoggerFactory.getLogger(ConnectionTest.class);

    private final static String DB_URL = "jdbc:mysql://10.1.11.110:3310/baojia_bike?zeroDateTimeBehavior" +
            "=convertToNull&useSSL" +
            "=false&serverTimezone=GMT%2B8";


    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, "baojia_xm", "DgisNKhg");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


    @Test
    public void query() throws SQLException {
        String sql = "select * from tb_test";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            int counts = resultSet.getInt("counts");
            int p_nums = resultSet.getInt("p_nums");
            logger.info("id:{}, counts:{}, p_nums:{}", id, counts, p_nums);
        }

        connection.close();


    }


}
