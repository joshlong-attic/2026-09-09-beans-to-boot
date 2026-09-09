package com.example.bootiful_beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.awt.desktop.AppReopenedListener;
import java.util.Collection;


// dependency injection
// portable service abstractions
// aop - aspect oriented programming
// autoconfiguration

@SpringBootApplication
public class BootifulBeansApplication
        implements ApplicationRunner {

    private final CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(BootifulBeansApplication.class, args);
    }

    BootifulBeansApplication(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerService.findAll().forEach(IO::println);
    }
}

@Service
@Transactional
class JdbcCustomerService implements CustomerService {

    private final JdbcClient jdbcClient;

    JdbcCustomerService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Collection<Customer> findAll() {
        return this.jdbcClient
                .sql(" select * from customer ")
                .query((rs, _) -> new Customer(rs.getInt("id"),
                        rs.getString("name")))
                .list();
    }
}

interface CustomerService {

    Collection<Customer> findAll();
}

record Customer(int id, String name) {
}
