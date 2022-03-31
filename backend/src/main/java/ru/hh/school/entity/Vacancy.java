package ru.hh.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Vacancy {

    @Id
    private long id;
    private String name;
    private LocalDateTime dateCreate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    private Area area;
    private long salaryTo;
    private long salaryFrom;
    private String salaryCurrency;
    private boolean salary_gross;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;
    private long viewCount;
    private String comment;
}
