package io.generator.barcode.project;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(JPAConfig.class)
@Configuration
@ComponentScan("io.generator.barcode.project")
public class AppConfig {



}
