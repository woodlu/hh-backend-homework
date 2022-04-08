package ru.hh.school.dto.employer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.hh.school.dto.AreaDto;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerDto {
    private long id;
    private String name;
    private String description;
    private AreaDto area;
}
