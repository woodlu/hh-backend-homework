package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import ru.hh.school.dto.employer.EmployerAddDto;
import ru.hh.school.dto.employer.EmployerEditDto;
import ru.hh.school.dto.employer.FavouriteEmployerDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favourite/employer")
@AllArgsConstructor
public class FavouriteEmployerResource {

    private EmployerService employerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addEmployer(EmployerAddDto employerAddDto) {
        return Response.ok(employerService.addEmployerToFavourite(employerAddDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FavouriteEmployerDto> getEmployers(@QueryParam("page") Integer page,
                                                   @QueryParam("per_page") Integer perPage) {
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
