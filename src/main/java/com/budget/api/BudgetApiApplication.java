package com.budget.api;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;

@SpringBootApplication
public class BudgetApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BudgetApiApplication.class, args);
  }


  @Bean
  public Formatter<LocalDate> localDateFormatter() {
    return new Formatter<LocalDate>() {
      @Override
      public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      }

      @Override
      public String print(LocalDate object, Locale locale) {
        return  DateTimeFormatter.ofPattern("dd/MM/yyyy").format(object);
      }
    };
  }
}
