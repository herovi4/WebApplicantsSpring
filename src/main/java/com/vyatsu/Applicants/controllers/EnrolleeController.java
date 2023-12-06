package com.vyatsu.Applicants.controllers;

import com.vyatsu.Applicants.DB.*;
import com.vyatsu.Applicants.dao.EnrolleeListDao;
import com.vyatsu.Applicants.dao.ExamListDao;
import com.vyatsu.Applicants.models.Enrollee;
import com.vyatsu.Applicants.models.Exam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.vyatsu.Applicants.dao.ExamListDao.getExamsByEnrolleeId;

@Controller
public class EnrolleeController {
    EnrolleService enrolleService = new EnrolleService(new EnrolleeDBDao());
    ExamService examService = new ExamService(new ExamDBDao());

    int idExam;

    public EnrolleeController() throws SQLException, ClassNotFoundException {
    }

    @GetMapping("/enrollees")
    public String enrollees(Model model){
        model.addAttribute("title","Список абитуриентов");
        List<Enrollee> enrollees = enrolleService.getAllEnrolles();
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }


    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable int id, Model model) throws SQLException {
        Enrollee enrollee = enrolleService.getAllEnrolles().get(id);
        model.addAttribute("enrollee", enrollee);
        List<Exam> exams = examService.getExamsByEnrolleeId((int) enrollee.getId());
        model.addAttribute("exams", exams);
        return "enrollee";
    }

    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title","Добавление абитуриента");
        Enrollee enrollee = new Enrollee ();
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number", enrolleService.sizeEnrollees());
        return "add";
    }
    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee,
                                 Model model) {
        if(enrollee.isNotNull()){
            int currentId = enrolleService.getAllEnrolles().size();
            enrollee.setId(currentId);
            enrolleService.save(enrollee);
            List<Enrollee> enrollees = enrolleService.getAllEnrolles();
            model.addAttribute("enrollees", enrollees);
            return "redirect:/enrollees";
        }
        return "redirect:/add";
    }

    @GetMapping("/exam{id}")
    public String examForm(@PathVariable int id, Model model) {
        idExam=id;
        model.addAttribute("title","Добавление экзамена'");
        List<String> subjects = new ArrayList<>();
        subjects.add("Математика");
        subjects.add("Физика");
        subjects.add("Информатика");
        subjects.add("Биология");
        subjects.add("Русский язык");
        model.addAttribute("subjects", subjects);
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        model.addAttribute("id", id);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(@ModelAttribute Exam exam,
                             Model model) {
        if(examService.getAllExams().stream().
                filter(x ->Objects.equals(x.getSubject(), exam.getSubject()) && Objects.equals(x.getIdEnrollee(), idExam))
                        .toList().isEmpty())
        {
            exam.setIdEnrollee(idExam);
            examService.save(exam);
            model.addAttribute("exams", exam);
        }
        return "redirect:/enrollees";
    }
}
