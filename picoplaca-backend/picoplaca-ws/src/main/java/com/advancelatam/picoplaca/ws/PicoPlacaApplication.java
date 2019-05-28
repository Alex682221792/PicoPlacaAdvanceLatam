package com.advancelatam.picoplaca.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@EntityScan(basePackageClasses = {
        PicoPlacaApplication.class,
        Jsr310JpaConverters.class
    }, basePackages = {"com.advancelatam.picoplaca.cliente"})
@ComponentScan({"com.advancelatam.picoplaca.*"})
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories(basePackages = {"com.advancelatam.picoplaca.cliente"})
public class PicoPlacaApplication {
  @PostConstruct
  public void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    SpringApplication.run(PicoPlacaApplication.class, args);
  }
}
