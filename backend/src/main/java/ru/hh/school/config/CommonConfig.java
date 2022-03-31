package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.hhapiclient.HHApiClient;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.resource.FavouriteEmployerResource;
import ru.hh.school.service.EmployerService;

@Configuration
@Import({
  // import your beans here
  ExampleResource.class,
  NabCommonConfig.class,
        HHApiClient.class,
        EmployerResource.class,
        FavouriteEmployerResource.class,
        EmployerDao.class,
        AreaDao.class,
        EmployerService.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
