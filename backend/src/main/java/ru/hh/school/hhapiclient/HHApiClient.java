package ru.hh.school.hhapiclient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployersItemDto;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Singleton
@RequiredArgsConstructor
public class HHApiClient {

    private final Client client = ClientBuilder.newClient();
    FileSettings fileSettings;

    private String URL;
    private String EMPLOYER_LINK;
    private String VACANCY_LINK;
    private String USER_AGENT_HEADER;
    private String USER_AGENT_HEADER_VALUE;

    public HHApiClient(FileSettings fileSettings) {
        this.fileSettings = fileSettings;
        URL = fileSettings.getString("hh.url");
        EMPLOYER_LINK = fileSettings.getString("hh.employerLink");
        VACANCY_LINK = fileSettings.getString("hh.vacancyLink");
        USER_AGENT_HEADER = fileSettings.getString("hh.userAgentHeader");
        USER_AGENT_HEADER_VALUE = fileSettings.getString("hh.userAgentHeaderValue");
    }

    public EmployerDto getEmployer(long employerId) {
        String path = EMPLOYER_LINK + employerId;
        EmployerDto employerDto = client.target(URL).path(path).request()
                .header(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
                .get(EmployerDto.class);

        return employerDto;
    }

    public EmployersItemDto getEmployers(int page, int perPage, String query) {
        EmployersItemDto employersItemDto = client.target(URL).path(EMPLOYER_LINK)
                .queryParam("page", page)
                .queryParam("per_page", perPage)
                .queryParam("query", query)
                .request()
                .header(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
                .get(EmployersItemDto.class);

        return employersItemDto;
    }
}
