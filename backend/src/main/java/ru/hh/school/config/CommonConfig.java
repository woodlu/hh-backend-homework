package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.hhapiclient.HHApiClient;
import ru.hh.school.resource.*;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.VacancyService;
import ru.hh.school.service.converter.Converters;
import ru.hh.school.validator.Validator;

@Configuration
@Import({
  // import your beans here
  NabCommonConfig.class,
        ExampleResource.class,
        HHApiClient.class,
        Converters.class,
        EmployerResource.class,
        FavouriteEmployerResource.class,
        VacancyResource.class,
        FavouriteVacancyResource.class,
        EmployerDao.class,
        AreaDao.class,
        VacancyDao.class,
        EmployerService.class,
        VacancyService.class,
        Validator.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
