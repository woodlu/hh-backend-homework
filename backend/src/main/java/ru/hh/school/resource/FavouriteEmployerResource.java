package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.employer.EmployerAddDto;
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
        System.out.println("fav emp res " + employerAddDto);
        return Response.ok(employerService.addEmployerToFavourite(employerAddDto)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employer> getEmployers() {
        return employerService.getEmployersFromFavourite();
    }
}
