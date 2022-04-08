package ru.hh.school.dto.employer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.hh.school.dto.AreaDto;

import javax.persistence.*;

@Data
@Builder
public class FavouriteEmployerDto {
    private long id;
    private String name;
    @JsonProperty(value = "date_create")
    private String dateCreate;
    private String description;
    private AreaDto area;
    private String comment;
    @Column(name = "view_count")
    private long viewCount;
    private String popularity;
}
