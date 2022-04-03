package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.employer.EmployerAddDto;
import ru.hh.school.dto.employer.FavouriteEmployerDto;
import ru.hh.school.dto.vacancy.FavouriteVacancyDto;
import ru.hh.school.dto.vacancy.VacancyAddDto;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favourite/vacancy")
@AllArgsConstructor
public class FavouriteVacancyResource {
    private VacancyService vacancyService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addVacancy(VacancyAddDto vacancyAddDto) {
        return Response.ok(vacancyService.addVacancyToFavourite(vacancyAddDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FavouriteVacancyDto> getEmployers(@QueryParam("page") Integer page,
                                                  @QueryParam("per_page") Integer perPage) {
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
