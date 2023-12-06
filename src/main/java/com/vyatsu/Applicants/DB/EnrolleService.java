package com.vyatsu.Applicants.DB;

import com.vyatsu.Applicants.dao.Dao;
import com.vyatsu.Applicants.models.Enrollee;

import java.util.ArrayList;
import java.util.List;

public class EnrolleService {

    private Dao enrolleeDao;

    public EnrolleService(Dao enrolleeDao) {
        this.enrolleeDao = enrolleeDao;
    }

    public long sizeEnrollees() {
        return  enrolleeDao.size();
    }

    public List<Enrollee>  getAllEnrolles() {
        return enrolleeDao.getAll().stream().map(x -> new Enrollee((EnrolleeEntity) x)).toList();
    }

    public Enrollee getEnrollee (long id) {
        return new Enrollee((EnrolleeEntity) enrolleeDao.get(id).get());
    }

    public void save(Enrollee enrollee) {
        enrollee.setId(enrolleeDao.size());
        EnrolleeEntity enrolleeEntity = new EnrolleeEntity(enrollee);
        enrolleeDao.save(enrolleeEntity);
    }
    public List<Enrollee> getEnrolles(List<EnrolleeEntity> enrolleeEntitys) {
        List<Enrollee> enrollees = new ArrayList<>();
        for (EnrolleeEntity enrolleeEntity: enrolleeEntitys) {
            enrollees.add(new Enrollee(enrolleeEntity));
        }
        return enrollees;
    }
}

