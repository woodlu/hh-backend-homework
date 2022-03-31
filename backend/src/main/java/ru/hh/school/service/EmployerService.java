package ru.hh.school.service;

import lombok.AllArgsConstructor;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.employer.EmployerAddDto;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployerListDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.hhapiclient.HHApiClient;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
@AllArgsConstructor
public class EmployerService {
    private HHApiClient client;
    private EmployerDao employerDao;
    private AreaDao areaDao;

    public EmployerDto getEmployerFromHH(long employerId) {
        return client.getEmployer(employerId);
    }

    public List<EmployerListDto> getEmployersFromHH(int page, int perPage, String query) {
        return client.getEmployers(page, perPage, query).getItems();
    }

    @Transactional
    public String addEmployerToFavourite(EmployerAddDto employerAddDto) {
        System.out.println("service addEmployerToFavourite:");
        EmployerDto employerDto = getEmployerFromHH(employerAddDto.getEmployerId());
        Employer employer = convertEmployerDtoToEmployer(employerDto, employerAddDto.getComment());
        System.out.println(employer);
        addAreaToDatabase(employer.getArea());
        employerDao.addEmployerToFavourite(employer);
        return "Employer added";
    }

    @Transactional
    public List<Employer> getEmployersFromFavourite() {
        System.out.println("service getEmployersFromFavourite:");
        return employerDao.getEmployers();
    }

    @Transactional
    public String addAreaToDatabase(Area area) {
        System.out.println("service addAreaToDatabase:");
        areaDao.addArea(area);
        return "Area added";
    }

    private Employer convertEmployerDtoToEmployer(EmployerDto employerDto, String comment) {
        Employer employer = Employer.builder()
                .id(employerDto.getId())
                .name(employerDto.getName())
                .description(employerDto.getDescription())
                .area(
                        Area.builder()
                                .id(employerDto.getArea().getId())
                                .name(employerDto.getArea().getName())
                                .build()
                )
                .comment(comment)
                .viewCount(1)
                .build();
        return employer;
    }


}
