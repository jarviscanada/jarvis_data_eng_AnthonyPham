package ca.jrvs.apps.jdbc;

import java.sql.*;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class JDBCExecutor {

    final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    public static void main(String... args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport", "postgres", "password");
        JDBCExecutor jdbcEx = new JDBCExecutor();
        try{
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
            Order order2 = orderDAO.findById(1001);
            Order order3 = orderDAO.findById(1002);
            System.out.println(order);
            System.out.println(order2);
            System.out.println(order3);

        }
        catch(SQLException e){
            jdbcEx.logger.error("Error: Connection failed", e);
        }
    }
}