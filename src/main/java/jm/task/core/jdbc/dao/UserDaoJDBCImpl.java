package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.model.core.ID;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
//Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS mysqldb.User" +
                "(id mediumint not null auto_increment," +
                " name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age tinyint, " +
                "PRIMARY KEY (id))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("При создании таблицы произошла ошибка");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE if exists mysqldb.User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate(sql);
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("При удалении таблицы произошла ошибка");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO mysqldb.User (name, lastname, age) VALUE (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Пользователь с именем " + name + " НЕ добавлен в базу данных.");
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM mysqldb.User WHERE id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
            System.out.println("Пользователь удален");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Удаление пользоватля не удалось");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM mysqldb.User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
                System.out.println("Пользователи получены");
            }
        } catch (SQLException e) {
            System.err.println("Получение пользователей не удалось");
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE mysqldb.User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("При очищении таблицы произошла ошибка");
        }

    }
}
