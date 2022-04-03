package ru.hh.school.service;

import lombok.AllArgsConstructor;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.vacancy.FavouriteVacancyDto;
import ru.hh.school.dto.vacancy.VacancyAddDto;
import ru.hh.school.dto.vacancy.VacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.hhapiclient.HHApiClient;
import ru.hh.school.service.converter.Converters;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@AllArgsConstructor
@Transactional
public class VacancyService {

    private HHApiClient client;
    private VacancyDao vacancyDao;
    private AreaDao areaDao;
    private Converters converters;
    private EmployerDao employerDao;

    public VacancyDto getVacancyFromHH(long vacancyId) {
        return client.getVacancy(vacancyId);
    }

    public List<VacancyDto> getVacanciesFromHH(int page, int perPage, String query) {
        return client.getVacancies(page, perPage, query).getItems();
    }

    public String addVacancyToFavourite(VacancyAddDto vacancyAddDto) {
        Vacancy vacancy = vacancyDao.getVacancy(vacancyAddDto.getVacancyId());
        if (vacancy != null) return "Vacancy already exists";
        VacancyDto vacancyDto = getVacancyFromHH(vacancyAddDto.getVacancyId());
        System.out.println(vacancyDto);
        Area area = getAreaForVacancy(vacancyDto);
        Employer employer = getEmployerForVacancy(vacancyDto, area);
        vacancy = converters.convertVacancyDtoToVacancy(vacancyDto, vacancyAddDto.getComment(), area, employer);
//        if (employer.getArea().getId() == vacancy.getArea().getId()) employer.setArea(area);
        vacancyDao.addVacancyToFavourite(vacancy);
        return "Vacancy added";
    }

    public List<FavouriteVacancyDto> getVacanciesFromFavourite(int page, int perPage) {
        List<Vacancy> vacancies = vacancyDao.getVacancies(page, perPage);
        return vacancies.stream()
                .map(vacancy -> converters.convertVacancyToFavouriteVacancyDto(vacancy))
                .collect(Collectors.toList());
    }

    public void deleteVacancy(long vacancyId) {
        vacancyDao.deleteVacancy(vacancyId);
    }

    private Area getAreaForVacancy(VacancyDto vacancyDto) {
        Area area = areaDao.getArea(vacancyDto.getArea().getId());
        if (area == null) {
            area =  Area.builder()
                    .id(vacancyDto.getArea().getId())
                    .name(vacancyDto.getArea().getName())
                    .build();
        }
        return area;
    }

    private Employer getEmployerForVacancy(VacancyDto vacancyDto, Area area) {
        Employer employer = employerDao.getEmployer(vacancyDto.getEmployer().getId());
        if (employer == null) {
            EmployerDto employerDto = client.getEmployer(vacancyDto.getEmployer().getId());
            if (area.getId() != employerDto.getArea().getId()) area = Area.builder()
                    .id(employerDto.getArea().getId())
                    .name(employerDto.getArea().getName())
                    .build();
            employer = converters.convertEmployerDtoToEmployer(employerDto, null, area);
        }
        return employer;
    }

    public String refreshVacancy(long vacancyId) {
        Vacancy vacancy = vacancyDao.getVacancy(vacancyId);
        if (vacancy == null) return "Vacancy doesn't exist";
        VacancyDto newVacancyDto = client.getVacancy(vacancyId);
        Area area = vacancy.getArea();
        Area employerArea = vacancy.getEmployer().getArea();
        Area newVacancyArea = Area.builder()
                .id(newVacancyDto.getArea().getId())
                .name(newVacancyDto.getArea().getName())
                .build();

        if (newVacancyArea.getId() != area.getId()) vacancy.setArea(newVacancyArea);
        else vacancy.getArea().setName(newVacancyDto.getArea().getName());
        if (employerArea.getId() == newVacancyArea.getId()) {
            vacancy.getEmployer().setArea(newVacancyArea);
            areaDao.detach(employerArea);
        }
        vacancy.setName(newVacancyDto.getName());
        vacancy.setSalaryCurrency(newVacancyDto.getSalary().getCurrency());
        vacancy.setSalaryFrom(newVacancyDto.getSalary().getFrom());
        vacancy.setSalaryTo(newVacancyDto.getSalary().getTo());
        vacancy.setSalaryGross(newVacancyDto.getSalary().isGross());
        return "Vacancy " + vacancyId + " refreshed";
    }
}
