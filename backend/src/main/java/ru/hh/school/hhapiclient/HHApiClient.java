package ru.hh.school.hhapiclient;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.employer.EmployerDto;
import ru.hh.school.dto.employer.EmployersItemDto;
import ru.hh.school.dto.vacancy.VacanciesItemDto;
import ru.hh.school.dto.vacancy.VacancyDto;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Singleton
public class HHApiClient {

    private final Client client;
    private FileSettings fileSettings;

    private final String URL;
    private final String EMPLOYER_LINK;
    private final String VACANCY_LINK;
    private final String USER_AGENT_HEADER;
    private final String USER_AGENT_HEADER_VALUE;

    public HHApiClient(FileSettings fileSettings) {
        this.fileSettings = fileSettings;
        this.client = ClientBuilder.newClient();
        URL = fileSettings.getString("hh.url");
        EMPLOYER_LINK = fileSettings.getString("hh.employerLink");
        VACANCY_LINK = fileSettings.getString("hh.vacancyLink");
        USER_AGENT_HEADER = fileSettings.getString("hh.userAgentHeader");
        USER_AGENT_HEADER_VALUE = fileSettings.getString("hh.userAgentHeaderValue");
    }

    public EmployerDto getEmployer(long employerId) {
        return client.target(URL).path(EMPLOYER_LINK + employerId).request()
                .header(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
                .get(EmployerDto.class);
    }

    public EmployersItemDto getEmployers(int page, int perPage, String query) {
        EmployersItemDto employersItemDto = client.target(URL).path(EMPLOYER_LINK)
                .queryParam("page", page)
                .queryParam("per_page", perPage)
                .queryParam("text", query)
                .request()
                .header(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
                .get(EmployersItemDto.class);

        return employersItemDto;
    }

    public VacancyDto getVacancy(long vacancyId) {
        return client.target(URL).path(VACANCY_LINK + vacancyId).request()
                .header(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
                .get(VacancyDto.class);
    }

    public VacanciesItemDto getVacancies(int page, int perPage, String query) {
        VacanciesItemDto vacanciesItemDto = client.target(URL).path(VACANCY_LINK)
                .queryParam("page", page)
                .queryParam("per_page", perPage)
                .queryParam("text", query)
                .request()
                .header(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
                .get(VacanciesItemDto.class);

        return vacanciesItemDto;
    }
}
