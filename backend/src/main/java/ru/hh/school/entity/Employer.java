package ru.hh.school.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ru.hh.nab.common.properties.FileSettings;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Builder
@PropertySource(value = {"classpath:hh.properties"})
public class Employer {
    public Employer(long id, String name, LocalDateTime dateCreate, String description, Area area, String comment, long viewCount) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.description = description;
        this.area = area;
        this.comment = comment;
        this.viewCount = viewCount;
    }

    @Id
    private long id;
    private String name;
    @Column(name = "date_create")
    @CreationTimestamp
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateCreate;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;
    private String comment;
    @Column(name = "view_count")
    private long viewCount;
    @Transient
    private String popularity;

    @JsonGetter(value = "popularity")
    private String getThePopularity() {
        this.popularity = viewCount > 50 ? "POPULAR" : "REGULAR";
        return popularity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public String getDescription() {
        return description;
    }

    public Area getArea() {
        return area;
    }

    public String getComment() {
        return comment;
    }

    public long getViewCount() {
        return viewCount;
    }

}
