package ru.hh.school.dto.employer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployerAddDto {
    @JsonProperty(value = "employer_id")
    private long employerId;
    private String comment;
}
