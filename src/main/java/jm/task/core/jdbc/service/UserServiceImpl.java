package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBCimpl = new UserDaoJDBCImpl();


    public void createUsersTable() throws SQLException {
        userDaoJDBCimpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBCimpl.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoJDBCimpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        userDaoJDBCimpl.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoJDBCimpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBCimpl.cleanUsersTable();
    }
}
