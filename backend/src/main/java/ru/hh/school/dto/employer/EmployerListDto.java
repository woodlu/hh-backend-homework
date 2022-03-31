package ru.hh.school.dto.employer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerListDto {
    private long id;
    protected String name;
}
