package com.vyatsu.Applicants.DB;

import com.vyatsu.Applicants.dao.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EnrolleeDBDao implements Dao<EnrolleeEntity> {

    private H2Connection h2Connection;

    public EnrolleeDBDao() throws SQLException, ClassNotFoundException {
        h2Connection = H2Connection.getH2Connection();
        Statement statement = h2Connection.getConnection().createStatement();
        String s = "CREATE TABLE IF NOT EXISTS ENROLLEE" +
                "(id number primary key not null," +
                " birthday date not null, " +
                " fullName varchar(30) not null );" +
                "INSERT INTO ENROLLEE (id, birthday, fullName)\n" +
                "VALUES (0, '2003-03-23', 'Иванов Иван Иванович');";
        // добавьте свои записи
        statement.execute(s);
        statement.close();
    }

    @Override
    public Optional<EnrolleeEntity> get(long id) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ENROLLEE WHERE ID = " + id);
            if (resultSet.next()) {
                Long enrolleeId = resultSet.getLong("ID");
                String fullName = resultSet.getString("FULLNAME");
                java.sql.Date birthday = resultSet.getDate("BIRTHDAY");

                EnrolleeEntity enrolleeEntity = new EnrolleeEntity();
                enrolleeEntity.setId(enrolleeId);
                enrolleeEntity.setFullName(fullName);
                enrolleeEntity.setBirthday(birthday);

                statement.close();
                return Optional.of(enrolleeEntity);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public int size() {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM ENROLLEE");
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                statement.close();
                return count;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
        @Override
    public List<EnrolleeEntity> getAll() {
        try {
            List<EnrolleeEntity> enrolleeEntities = new ArrayList<>();
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from ENROLLEE");
            while (resultSet.next()) {
                Long id= resultSet.getLong("ID");
                String fullName = resultSet.getString("FULLNAME");
                java.sql.Date birthday = resultSet.getDate("BIRTHDAY");
                EnrolleeEntity enrolleeEntity = new EnrolleeEntity();
                enrolleeEntity.setId(id);
                enrolleeEntity.setFullName(fullName);
                enrolleeEntity.setBirthday(birthday);
                enrolleeEntities.add(enrolleeEntity);
            }
            statement.close();
            return enrolleeEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void save(EnrolleeEntity enrollee) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            String s = String.format("insert into ENROLLEE values (%s, '%s', '%s')",
                    enrollee.getId(), enrollee.getBirthday(),enrollee.getFullName());
            statement.execute(s);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(EnrolleeEntity enrolleeEntity) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String deleteQuery = "DELETE FROM ENROLLEE WHERE ID = " + enrolleeEntity.getId();
            statement.execute(deleteQuery);
            statement.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}