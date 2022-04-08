package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.SalaryDto;

@Data
@Builder
public class FavouriteVacancyDto {
    private long id;
    private String name;
    @JsonProperty(value = "date_create")
    private String dateCreate;
    private AreaDto area;
    private SalaryDto salary;
    @JsonProperty(value = "created_at")
    private String createdAt;
    private EmployerForVacancyDto employer;
    private String popularity;
    @JsonProperty(value = "views_count")
    private long viewsCount;
    private String comment;
}
