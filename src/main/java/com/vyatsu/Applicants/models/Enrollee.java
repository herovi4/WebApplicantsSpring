package com.vyatsu.Applicants.models;

import com.vyatsu.Applicants.DB.EnrolleeEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollee {
    long id;
    @NotNull
    @Size(min=2, max=30)
    String fullName;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    java.sql.Date birthday;
    public Enrollee(EnrolleeEntity enrolleeEntity) {
        this.id= Math.toIntExact(enrolleeEntity.getId());
        this.birthday=enrolleeEntity.getBirthday();
        this.fullName=enrolleeEntity.getFullName();
    }
    public String getFormattedBirthday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(birthday);
    }
    public boolean isNotNull(){ return fullName!=null && birthday!=null;}
}
