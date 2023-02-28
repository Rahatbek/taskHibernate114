package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "rahat", "mysql");
                 Statement statement = conn.createStatement();) {
                statement.executeUpdate("create table if not exists user(id bigint not null AUTO_INCREMENT PRIMARY KEY, name varchar(255), lastName varchar(255), age tinyint)");

                statement.execute("insert into user(name, lastName, age) values('Rahat', 'Kozhantaev', 30)");
                ResultSet rs = statement.executeQuery("select name from users.user where name = 'Rahat'");
                User user = new User();
                while (rs.next()) {
                    user.setName(rs.getString("name"));
                    System.out.println("1-user: " + user.getName());
                }

                statement.execute("insert into user(name, lastName, age) values('Roma', 'Romanov', 26)");
                rs = statement.executeQuery("select name from users.user where name = 'Roma'");
                while (rs.next()) {
                    user.setName(rs.getString("name"));
                    System.out.println("2-user: " + user.getName());
                }

                statement.execute("insert into user(name, lastName, age) values('Viktor', 'Viktorov', 28)");
                rs = statement.executeQuery("select name from users.user where name = 'Viktor'");
                while (rs.next()) {
                    user.setName(rs.getString("name"));
                    System.out.println("3-user: " + user.getName());
                }

                statement.execute("insert into user(name, lastName, age) values('Anton', 'Antonov', 24)");
                rs = statement.executeQuery("select name from users.user where name = 'Anton'");
                while (rs.next()) {
                    user.setName(rs.getString("name"));
                    System.out.println("4-user: " + user.getName());
                }

                List<User> users = new ArrayList<>();
                rs = statement.executeQuery("select * from user");
                while (rs.next()) {
                    User userForList = new User();
                    userForList.setId(rs.getLong("id"));
                    userForList.setName(rs.getString("name"));
                    userForList.setLastName(rs.getString("lastName"));
                    userForList.setAge(rs.getByte("age"));
                    users.add(userForList);
                }
                System.out.println("Получение всех: " + users);

                statement.executeUpdate("delete from users.user");

                statement.executeUpdate("drop table if exists user");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}
