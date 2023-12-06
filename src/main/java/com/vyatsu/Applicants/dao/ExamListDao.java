package com.vyatsu.Applicants.dao;

import com.vyatsu.Applicants.models.Exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamListDao implements Dao<Exam> {
    private static List<Exam> exams = new ArrayList<>();
    public ExamListDao (){
        exams.add(new Exam(84, "Математка",0));
        exams.add(new Exam(85, "Русский язык", 0));

    }
    public static List<Exam> getExamsByEnrolleeId(int idEnrollee) {
        List<Exam> examList;
        examList = exams.stream().filter(x -> x.getIdEnrollee()==idEnrollee).toList();
        return examList;
    }
    public static List<String> getSubjectsByEnrolleeId() {
        return exams.stream()
                .map(Exam::getSubject)
                .toList();
    }

    @Override
    public Optional<Exam> get(long id) {
        return Optional.of(exams.get((int) id));
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public List<Exam> getAll() {
        return exams;
    }

    @Override
    public void save(Exam exam) {
        exams.add(exam);
    }

    @Override
    public void delete(Exam exam) {
        exams.remove(exam);
    }
}
