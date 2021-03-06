package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configurable
@EnableTransactionManagement
@ComponentScan(basePackages="pl.java.scalatech.service",useDefaultFilters=false,includeFilters={@Filter(Service.class)})
public class ServiceConfig {

}