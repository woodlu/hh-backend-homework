package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployerListDto;
import ru.hh.school.hhapiclient.HHApiClient;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/employer")
@AllArgsConstructor
public class EmployerResource {

    private final EmployerService employerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/{employerId}")
    public EmployerDto getEmployer(@PathParam("employerId") Long employerId) {
        System.out.println("lol");
        return employerService.getEmployerFromHH(employerId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployerListDto> getEmployers(@QueryParam("page") Integer page,
                                              @QueryParam("per_page") Integer perPage,
                                              @QueryParam("query") String query) {
        return employerService.getEmployersFromHH(page, perPage, query);
    }
}
