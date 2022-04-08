package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.vacancy.FavouriteVacancyDto;
import ru.hh.school.dto.vacancy.VacancyAddDto;
import ru.hh.school.service.VacancyService;
import ru.hh.school.validator.Validator;

import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favourite/vacancy")
@AllArgsConstructor
public class FavouriteVacancyResource {
    private VacancyService vacancyService;
    private Validator validator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addVacancy(VacancyAddDto vacancyAddDto) {
        return Response.ok(vacancyService.addVacancyToFavourite(vacancyAddDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FavouriteVacancyDto> getEmployers(@QueryParam("page") @DefaultValue(value = "0") @Min(value = 0, message = "page should be >= 0")  Integer page,
                                                  @QueryParam("per_page") @DefaultValue(value = "20") @Max(value = 100, message = "per_page should be <= 100") Integer perPage) {
        validator.validatePagePerPageVacancies(page, perPage);
        return vacancyService.getVacanciesFromFavourite(page, perPage);
    }

    @DELETE
    @Path("/{vacancyId}")
    public Response deleteVacancy(@PathParam("vacancyId") Long vacancyId) {
        vacancyService.deleteVacancy(vacancyId);
        return Response.ok().build();
    }

    @POST
    @Path("/{vacancyId}/refresh")
    public Response refreshVacancy(@PathParam("vacancyId") Long vacancyId) {
        return Response.ok(vacancyService.refreshVacancy(vacancyId)).build();
    }
}
