package net.jdbc;


import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

/**
 * My test to make connection with postgres database and look simple table doctor
 * make train with simple connections
 */

public class JdbcDemoApp {

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin5555");

        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                var resultSet = statement.executeQuery("SELECT * FROM doctor;");
                while (resultSet.next()) {
                    var idName = resultSet.getString("id");
                    var fullName = resultSet.getString("fullName");
                    var specialist = resultSet.getString("specialist");
                    var age = resultSet.getString("age");

                    System.out.println(idName + " - " + fullName + " - " + specialist + " - " + age);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
