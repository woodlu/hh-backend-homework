package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.vacancy.VacancyDto;
import ru.hh.school.service.VacancyService;
import ru.hh.school.validator.Validator;

import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/vacancy")
@AllArgsConstructor
public class VacancyResource {

    private VacancyService vacancyService;
    private Validator validator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/{vacancyId}")
    public VacancyDto getVacancy(@PathParam("vacancyId") Long vacancyId) {
        return vacancyService.getVacancyFromHH(vacancyId);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacancyDto> getVacancies(@QueryParam("page") @DefaultValue(value = "0") @Min(value = 0, message = "page should be >= 0")  Integer page,
                                         @QueryParam("per_page") @DefaultValue(value = "20") @Max(value = 100, message = "per_page should be <= 100") Integer perPage,
                                         @QueryParam("query") String query) {
        validator.validatePagePerPageVacancies(page, perPage);
        return vacancyService.getVacanciesFromHH(page, perPage, query);
    }

}
