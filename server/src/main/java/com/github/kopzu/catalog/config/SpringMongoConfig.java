package com.github.kopzu.catalog.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@PropertySources(value = {@PropertySource("classpath:/application.properties")})
public class SpringMongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    @Override
    public String getDatabaseName() {
        return environment.getProperty("dbName");
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(environment.getProperty("dbUrl"));
    }
}