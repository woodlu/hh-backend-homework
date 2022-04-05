package ru.hh.school.service;

import lombok.AllArgsConstructor;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.employer.EmployerAddDto;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployerListDto;
import ru.hh.school.dto.employer.FavouriteEmployerDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.hhapiclient.HHApiClient;
import ru.hh.school.service.converter.Converters;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@AllArgsConstructor
@Transactional
public class EmployerService {
    private HHApiClient client;
    private EmployerDao employerDao;
    private AreaDao areaDao;
    private Converters converters;

    public EmployerDto getEmployerFromHH(long employerId) {
        return client.getEmployer(employerId);
    }

    public List<EmployerListDto> getEmployersFromHH(int page, int perPage, String query) {
        return client.getEmployers(page, perPage, query).getItems();
    }

    public String addEmployerToFavourite(EmployerAddDto employerAddDto) {
        Employer employer = employerDao.getEmployer(employerAddDto.getEmployerId());
        if (employer != null) return "Employer already exists";
        EmployerDto employerDto = getEmployerFromHH(employerAddDto.getEmployerId());
        Area area = areaDao.getArea(employerDto.getArea().getId());
        if (area == null) area = Area.builder().id(employerDto.getArea().getId())
                .name(employerDto.getArea().getName())
                .build();
        employer = converters.convertEmployerDtoToEmployer(employerDto, employerAddDto.getComment(), area);
        employerDao.addEmployerToFavourite(employer);
        return "Employer added";
    }

    public List<FavouriteEmployerDto> getEmployersFromFavourite(int page, int perPage) {
        List<Employer> employers = employerDao.getEmployers(page, perPage);
        incrementViewCount(employers);
        return employers.stream().map(employer -> converters.convertEmployerToFavouriteEmployerDto(employer)).collect(Collectors.toList());
    }

    public void editEmployer(long employerId, String comment) {
        employerDao.editEmployer(employerId, comment);
    }

    public void deleteEmployer(long employerId) {
        employerDao.deleteEmployer(employerId);
    }

    public String refreshEmployer(long employerId) {
        Employer employer = employerDao.getEmployer(employerId);
        if (employer == null) return "Employer doesn't exist";
        EmployerDto newEmployerDto = client.getEmployer(employerId);
        Area area = employer.getArea();
        Area newArea = Area.builder()
                .id(newEmployerDto.getArea().getId())
                .name(newEmployerDto.getArea().getName())
                .build();
        if (newArea.getId() != area.getId()) employer.setArea(newArea);
        else employer.getArea().setName(newEmployerDto.getArea().getName());
        employer.setName(newEmployerDto.getName());
        employer.setDescription(newEmployerDto.getDescription());
        return "Employer " + employerId + " refreshed";
    }


    public void incrementViewCount(List<Employer> employers) {
        employers.forEach(employer -> {
            employer.setViewCount(employer.getViewCount() + 1);
        });
    }

}
