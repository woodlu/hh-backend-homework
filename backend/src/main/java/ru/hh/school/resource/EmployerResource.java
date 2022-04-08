package ru.hh.school.resource;

import lombok.AllArgsConstructor;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployerListDto;
import ru.hh.school.service.EmployerService;
import ru.hh.school.validator.Validator;

import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/employer")
@AllArgsConstructor
public class EmployerResource {

    private final EmployerService employerService;
    private final Validator validator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/{employerId}")
    public EmployerDto getEmployer(@PathParam("employerId") Long employerId) {
        return employerService.getEmployerFromHH(employerId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployerListDto> getEmployers(@QueryParam("page") @DefaultValue(value = "0") @Min(value = 0, message = "page should be >= 0")  Integer page,
                                              @QueryParam("per_page") @DefaultValue(value = "20") @Max(value = 100, message = "per_page should be <= 100") Integer perPage,
                                              @QueryParam("query") String query) {
        validator.validatePagePerPageEmployers(page, perPage);
        return employerService.getEmployersFromHH(page, perPage, query);
    }
}
