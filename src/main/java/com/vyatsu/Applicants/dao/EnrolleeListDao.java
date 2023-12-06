package com.vyatsu.Applicants.dao;

import com.vyatsu.Applicants.models.Enrollee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EnrolleeListDao implements Dao<Enrollee> {

    private List<Enrollee> enrollees = new ArrayList<>();

    public EnrolleeListDao (){
    }

    @Override
    public Optional<Enrollee> get(long id) {
        return Optional.of(enrollees.get((int) id));
    }

    @Override
    public int size() {
        return enrollees.size();
    }

    @Override
    public List<Enrollee> getAll() {
        return enrollees;
    }

    @Override
    public void save(Enrollee enrollee) {
        enrollees.add(enrollee);
    }

    @Override
    public void delete(Enrollee enrollee) {
        enrollees.remove(enrollee);
    }
}
