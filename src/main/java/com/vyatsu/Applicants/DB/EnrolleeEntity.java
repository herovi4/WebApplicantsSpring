package com.vyatsu.Applicants.DB;

import com.vyatsu.Applicants.models.Enrollee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENROLLEE")
public class EnrolleeEntity {
    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "FULLNAME", length = 64, nullable = false)
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private java.sql.Date birthday;
    public EnrolleeEntity(Enrollee enrollee){
        this.id = enrollee.getId();
        this.fullName = enrollee.getFullName();
        this.birthday = transformDate(enrollee.getBirthday());
    }

    private java.sql.Date transformDate(java.util.Date date) {
        return new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
    }
    public String toString(){
        return String.valueOf(birthday.getDay() + '.' + birthday.getMonth() + '.' + birthday.getYear());
    }
}
