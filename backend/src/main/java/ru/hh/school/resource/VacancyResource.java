package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployerListDto;
import ru.hh.school.dto.vacancy.VacancyDto;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/vacancy")
@AllArgsConstructor
public class VacancyResource {

    private VacancyService vacancyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/{vacancyId}")
    public VacancyDto getVacancy(@PathParam("vacancyId") Long vacancyId) {
        return vacancyService.getVacancyFromHH(vacancyId);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacancyDto> getVacancies(@QueryParam("page") Integer page,
                                              @QueryParam("per_page") Integer perPage,
                                              @QueryParam("query") String query) {
        return vacancyService.getVacanciesFromHH(page, perPage, query);
    }

}
