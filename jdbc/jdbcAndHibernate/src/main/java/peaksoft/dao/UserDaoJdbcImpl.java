package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }
    public final Util util = new Util();

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR," +
                "last_name VARCHAR," +
                "age INT NOT NULL);";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("The table create successful.");
        } catch (SQLException e) {
            System.out.println("NOT CREATE!");
        }
    }

    public void dropUsersTable() {
        String dropUsers = "DROP TABLE IF EXISTS users;";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropUsers);
            System.out.println("The table is dropped!");
        } catch (SQLException e) {
            System.out.println("Not drop!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users(name, last_name, age) VALUES(?,?,?)";
        try (Connection connection = util.getConnection();
             PreparedStatement statement = connection.prepareStatement(save)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println(name + " " + "added");
        } catch (SQLException e) {
            System.out.println("!!!");
        }
    }


    public void removeUserById(long id) {
        String remove = "DROP TABLE IF EXISTS users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(remove);
            System.out.println("The table remove successful!");
        } catch (SQLException e) {
            System.out.println("<No>");
        }
    }

    public List<User> getAllUsers() {
        String getAllUsers = "SELECT * FROM users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsers);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                return users;
            }
        } catch (SQLException e) {
            System.out.println("!!!");
        }
        return null;
    }


    public void cleanUsersTable() {
        String clean = "DELETE FROM users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(clean);
            System.out.println("The table clean successful!");
        } catch (SQLException e) {
            System.out.println("!!!");
        }
    }
}