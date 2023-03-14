package net.jdbc;

import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

public class JdbcDemoApp {

    @SneakyThrows
    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin5555");

        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                var resultSet = statement.executeQuery("select * from doctor;");
                while (resultSet.next()) {
                    var fullName = resultSet.getString("fullname");
                    var spez = resultSet.getString("spez");
                    var age = resultSet.getString("age");

                    System.out.println(fullName + " - " + spez + " - " + age);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
