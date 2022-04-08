package ru.hh.school.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Vacancy {

    @Id
    private long id;
    private String name;
    @Column(name = "date_create")
    @CreationTimestamp
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dateCreate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id")
    private Area area;
    @Column(name = "salary_to")
    private long salaryTo;
    @Column(name = "salary_from")
    private long salaryFrom;
    @Column(name = "salary_currency")
    private String salaryCurrency;
    @Column(name = "salary_gross")
    private boolean salaryGross;
    @Column(name = "created_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id")
    private Employer employer;
    @Column(name = "view_count")
    private long viewCount;
    private String comment;
}
