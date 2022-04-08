package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.SalaryDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyDto {
    private long id;
    private String name;
    private AreaDto area;
    private SalaryDto salary;
    @JsonProperty(value = "created_at")
    private String createdAt;
    private EmployerForVacancyDto employer;
}
