package ru.hh.school.service.converter;

import lombok.AllArgsConstructor;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.SalaryDto;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.FavouriteEmployerDto;
import ru.hh.school.dto.vacancy.EmployerForVacancyDto;
import ru.hh.school.dto.vacancy.FavouriteVacancyDto;
import ru.hh.school.dto.vacancy.VacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.hhapiclient.HHApiClient;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Singleton
@AllArgsConstructor
public class Converters {
    private FileSettings fileSettings;
    private HHApiClient client;
    private AreaDao areaDao;

    public Employer convertEmployerDtoToEmployer(EmployerDto employerDto, String comment, Area area) {
        Employer employer = Employer.builder()
                .id(employerDto.getId())
                .name(employerDto.getName())
                .description(employerDto.getDescription())
                .area(area)
                .comment(comment)
                .viewCount(1)
                .build();
        return employer;
    }

    public FavouriteEmployerDto convertEmployerToFavouriteEmployerDto(Employer employer) {
        return FavouriteEmployerDto.builder()
                .id(employer.getId())
                .name(employer.getName())
                .dateCreate(employer.getDateCreate().toString())
                .area(AreaDto.builder()
                        .id(employer.getArea().getId())
                        .name(employer.getArea().getName())
                        .build())
                .comment(employer.getComment())
                .description(employer.getDescription())
                .viewCount(employer.getViewCount())
                .popularity(employer.getViewCount() > fileSettings.getInteger("hh.popularity") ?
                        "POPULAR" : "REGULAR")
                .build();
    }

    public Vacancy convertVacancyDtoToVacancy(VacancyDto vacancyDto, String comment, Area area, Employer employer) {
        Vacancy vacancy = Vacancy.builder()
                .id(vacancyDto.getId())
                .name(vacancyDto.getName())
                .area(area)
                .comment(comment)
                .viewCount(1)
                .salaryFrom(vacancyDto.getSalary().getFrom())
                .salaryTo(vacancyDto.getSalary().getTo())
                .salaryGross(vacancyDto.getSalary().isGross())
                .salaryCurrency(vacancyDto.getSalary().getCurrency())
                .createdAt(LocalDateTime.parse(vacancyDto.getCreatedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")))
                .employer(employer)
                .build();
        return vacancy;
    }

    public FavouriteVacancyDto convertVacancyToFavouriteVacancyDto(Vacancy vacancy) {
        return FavouriteVacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .dateCreate(vacancy.getDateCreate().toString())
                .area(AreaDto.builder()
                        .id(vacancy.getArea().getId())
                        .name(vacancy.getArea().getName())
                        .build())
                .salary(SalaryDto.builder()
                        .from(vacancy.getSalaryFrom())
                        .to(vacancy.getSalaryTo())
                        .gross(vacancy.isSalaryGross())
                        .currency(vacancy.getSalaryCurrency())
                        .build()
                )
                .createdAt(vacancy.getCreatedAt().toString())
                .employer(EmployerForVacancyDto.builder()
                        .id(vacancy.getEmployer().getId())
                        .name(vacancy.getEmployer().getName())
                        .build())
                .comment(vacancy.getComment())
                .viewsCount(vacancy.getViewCount())
                .popularity(vacancy.getViewCount() > fileSettings.getInteger("hh.popularity") ?
                        "POPULAR" : "REGULAR")
                .build();
    }
}
