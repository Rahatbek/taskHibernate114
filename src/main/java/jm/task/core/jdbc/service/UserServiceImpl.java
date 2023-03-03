package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

//    private final UserDaoJDBCImpl daoJDBCOrHibernate = new UserDaoJDBCImpl();
    private final UserDaoHibernateImpl daoJDBCOrHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        daoJDBCOrHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        daoJDBCOrHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        daoJDBCOrHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        daoJDBCOrHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = daoJDBCOrHibernate.getAllUsers();
        return allUsers;
    }

    public void cleanUsersTable() {
        daoJDBCOrHibernate.cleanUsersTable();
    }
}
