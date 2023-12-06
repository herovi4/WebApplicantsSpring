package com.vyatsu.Applicants.DB;

import com.vyatsu.Applicants.dao.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamDBDao implements Dao<ExamEntity> {

    static H2Connection h2Connection;

    public ExamDBDao() throws SQLException, ClassNotFoundException {
        h2Connection = H2Connection.getH2Connection();
        Statement statement = h2Connection.getConnection().createStatement();
        String s = "CREATE TABLE IF NOT EXISTS EXAM" +
                " (score number not null," +
                " subject varchar(30) primary key not null, " +
                " idEnrollee number not null );" +
                "INSERT INTO EXAM (score, subject, idEnrollee)\n" +
                "VALUES (85, 'Английский язык', 0);";
        statement.execute(s);
        statement.close();
    }

    public static List<ExamEntity> getExamsByEnrolleeId(int idEnrollee) throws SQLException {
        try {
            h2Connection = H2Connection.getH2Connection();
            Statement statement = h2Connection.getConnection().createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from EXAM WHERE IDENROLLEE = " + idEnrollee);
            if (resultSet.next()) {
                int score = resultSet.getInt("SCORE");
                String subject = resultSet.getString("SUBJECT");

                ExamEntity examEntity = new ExamEntity();
                examEntity.setScore(score);
                examEntity.setSubject(subject);

                statement.close();
                return List.of(examEntity);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public Optional<ExamEntity> get(long id) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EXAM WHERE ID = " + id);
            if (resultSet.next()) {
                int score = resultSet.getInt("SCORE");
                String subject = resultSet.getString("SUBJECT");
                int idEnrollee = resultSet.getInt("IDENROLLEE");

                ExamEntity examEntity = new ExamEntity();
                examEntity.setScore(score);
                examEntity.setSubject(subject);
                examEntity.setIdEnrollee(idEnrollee);

                statement.close();
                return Optional.of(examEntity);
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
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM EXAM");
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
    public List<ExamEntity> getAll() {
        try {
            List<ExamEntity> examEntities = new ArrayList<>();
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from EXAM");
            while (resultSet.next()) {
                int score = resultSet.getInt("SCORE");
                String subject = resultSet.getString("SUBJECT");
                int idEnrollee = resultSet.getInt("IDENROLLEE");
                ExamEntity examEntity = new ExamEntity();
                examEntity.setScore(score);
                examEntity.setSubject(subject);
                examEntity.setIdEnrollee(idEnrollee);
                examEntities.add(examEntity);
            }
            statement.close();
            return examEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            String s = String.format("insert into EXAM (score, subject, idEnrollee) values (%s, '%s', %s)",
                    examEntity.getScore(),examEntity.getSubject(), examEntity.getIdEnrollee());
            statement.execute(s);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String deleteQuery = "DELETE FROM EXAM WHERE ID = " + examEntity.getIdEnrollee();
            statement.execute(deleteQuery);
            statement.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

