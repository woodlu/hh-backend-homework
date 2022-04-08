package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.employer.EmployerAddDto;
import ru.hh.school.dto.employer.EmployerEditDto;
import ru.hh.school.dto.employer.FavouriteEmployerDto;
import ru.hh.school.service.EmployerService;
import ru.hh.school.validator.Validator;

import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favourite/employer")
@AllArgsConstructor
public class FavouriteEmployerResource {

    private EmployerService employerService;
    private Validator validator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addEmployer(EmployerAddDto employerAddDto) {
        return Response.ok(employerService.addEmployerToFavourite(employerAddDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FavouriteEmployerDto> getEmployers(@QueryParam("page") @DefaultValue(value = "0") @Min(value = 0, message = "page should be >= 0")  Integer page,
                                                   @QueryParam("per_page") @DefaultValue(value = "20") @Max(value = 100, message = "per_page should be <= 100") Integer perPage) {
        validator.validatePagePerPageEmployers(page, perPage);
        return employerService.getEmployersFromFavourite(page, perPage);
    }

    @PUT
    @Path("/{employerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEmployer(@PathParam("employerId") Long employerId, EmployerEditDto employerEditDto) {
        employerService.editEmployer(employerId, employerEditDto.getComment());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{employerId}")
    public Response deleteEmployer(@PathParam("employerId") Long employerId) {
        employerService.deleteEmployer(employerId);
        return Response.ok().build();
    }

    @POST
    @Path("/{employerId}/refresh")
    public Response refreshEmployer(@PathParam("employerId") Long employerId) {
        return Response.ok(employerService.refreshEmployer(employerId)).build();
    }
}
