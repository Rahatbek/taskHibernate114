package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        try (Connection connection = Util.getConnecting(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table user(id bigint not null AUTO_INCREMENT PRIMARY KEY, name varchar(255), lastName varchar(255), age tinyint)");
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void dropUsersTable() {
            try(Connection  connection = Util.getConnecting(); Statement statement = connection.createStatement()) {
                statement.executeUpdate("drop table if exists user");
            } catch (SQLException e) {
                System.out.println(e);
            }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into user(name, lastName, age) values(?, ?, ?)";
        try (Connection connecting = Util.getConnecting(); PreparedStatement preparedStmt = connecting.prepareStatement(query)) {
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, lastName);
            preparedStmt.setByte(3, age);
            preparedStmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeUserById(long id) {
        String query = "delete from user where id = ?";
        try (Connection connecting = Util.getConnecting(); PreparedStatement preparedStmt = connecting.prepareStatement(query)) {
            preparedStmt.setLong(1, id);
            preparedStmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connecting = Util.getConnecting(); Statement statement = connecting.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection  connection = Util.getConnecting(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from users.user");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
