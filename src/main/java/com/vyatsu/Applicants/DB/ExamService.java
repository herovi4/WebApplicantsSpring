package com.vyatsu.Applicants.DB;

import com.vyatsu.Applicants.dao.Dao;
import com.vyatsu.Applicants.models.Enrollee;
import com.vyatsu.Applicants.models.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamService {
    private Dao examDao;

    public ExamService(Dao examdao) {
        this.examDao = examdao;
    }

    public long sizeExam() {
        return  examDao.size();
    }

    public List<Exam> getAllExams() {
        return examDao.getAll().stream().map(x -> new Exam((ExamEntity) x)).toList();
    }
    public Exam getEnrollee (long id) {
        return new Exam((ExamEntity) examDao.get(id).get());
    }
    public List<Exam> getExamsByEnrolleeId(int idEnrollee) {
        List<Exam> examList;
        examList = getAllExams().stream().filter(x -> x.getIdEnrollee() == idEnrollee).toList();
        return examList;
    }
    public void save(Exam exam) {
        exam.setIdEnrollee(exam.getIdEnrollee());
        ExamEntity examEntity = new ExamEntity(exam);
        examDao.save(examEntity);
    }
    public List<Exam> getExam(List<ExamEntity> examEntitys) {
        List<Exam> exams = new ArrayList<>();
        for (ExamEntity examEntity: examEntitys) {
            exams.add(new Exam(examEntity));
        }
        return exams;
    }
}
