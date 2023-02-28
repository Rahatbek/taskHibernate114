package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "create table if not exists user(id bigint not null AUTO_INCREMENT PRIMARY KEY, name varchar(255), lastName varchar(255), age tinyint)";
            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
            System.out.println("when create table: " + sqlQuery);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "drop table if exists user";

            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
            System.out.println("when drop table: " + sqlQuery);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            NativeQuery insertsqlQuery = session.createSQLQuery("INSERT INTO user(name,lastName,age) VALUES (?,?,?)");

            insertsqlQuery.setParameter(1, name);
            insertsqlQuery.setParameter(2, lastName);
            insertsqlQuery.setParameter(3, age);
            insertsqlQuery.executeUpdate();
            session.getTransaction().commit();
            System.out.println("inserted");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            NativeQuery insertsqlQuery = session.createSQLQuery("delete from user where id = ?");

            insertsqlQuery.setParameter(1, id);
            insertsqlQuery.executeUpdate();
            session.getTransaction().commit();
            System.out.println("deleted");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            NativeQuery query = session.createSQLQuery("select * from user");
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                User user = new User();
                user.setId(Long.parseLong(row[0].toString()));
                user.setName(row[1].toString());
                user.setLastName(row[2].toString());
                user.setAge(Byte.parseByte(row[3].toString()));
                resultList.add(user);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        System.out.println(resultList);
        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "delete from users.user";

            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
            System.out.println("deleted table: " + sqlQuery);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
