package com.schultzco.webservices.config.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by wendel.schultz on 9/16/15.
 *
 * Intended for lazy development and regular refresh from CI builds.  Use -Dspring.profiles.active=seed-data
 *
 */
@Component
@Profile("seed-data")
public class SeedDataListener implements ApplicationListener {
    private static final String PROP_INITIALIZATION_SQL_FILES="database.initializers";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment environment;

    private ResourceDatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator populator = null;
System.out.println("SeedDataListener: creating populator");
        String sqlsToRunString = environment.getProperty(PROP_INITIALIZATION_SQL_FILES);
        if(sqlsToRunString != null){
System.out.println("SeedDataListener: found property for SQLs to run " + sqlsToRunString);
            String[] sqlsToRun = sqlsToRunString.split(",");
            populator = new ResourceDatabasePopulator();

            for(String sql: sqlsToRun){
System.out.println("SeedDataListener: adding classpath resource to run: " + sql);
                populator.addScript(new ClassPathResource(sql.trim()));
            }
        }

        return populator;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if(event != null && event instanceof ContextRefreshedEvent) {
            ResourceDatabasePopulator dp = createDatabasePopulator();
            if(dp != null){
                dp.execute(dataSource);
            }
        }

    }
}
