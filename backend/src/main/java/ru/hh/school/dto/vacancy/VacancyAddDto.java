package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class VacancyAddDto {
    @JsonProperty(value = "vacancy_id")
    private long vacancyId;
    private String comment;
}
