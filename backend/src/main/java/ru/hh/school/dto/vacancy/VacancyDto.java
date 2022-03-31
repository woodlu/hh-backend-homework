package ru.hh.school.dto.vacancy;

import lombok.Data;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.SalaryDto;
import ru.hh.school.dto.employer.EmployerDto;

import java.time.LocalDateTime;

@Data
public class VacancyDto {
    private long id;
    private String name;
    private AreaDto area;
    private SalaryDto salary;
    private LocalDateTime createdAt;
    private EmployerDto employer;
}
