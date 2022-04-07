package ru.hh.school.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({NabHibernateProdConfig.class, NabProdConfig.class, CommonConfig.class})
public class ProdConfig {

  @Bean
  public DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings) {
    FileSettings dataSourceSettings = fileSettings.getSubSettings("master");
    Properties poolProperties = dataSourceSettings.getSubProperties("pool");
    HikariConfig config = new HikariConfig(poolProperties);
    config.setJdbcUrl("jdbc:postgresql://postgres:5432/hh");
    config.setUsername("hh");
    config.setPassword("hh");
    config.setPoolName("master");
    config.setReadOnly(false);
    config.setValidationTimeout(config.getConnectionTimeout() + 100L);
    return dataSourceFactory.create(config, fileSettings, false);
//    return dataSourceFactory.create("master", false, fileSettings);
  }
}
