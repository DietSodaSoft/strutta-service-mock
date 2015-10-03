package com.schultzco.webservices.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Among other things, pull sensitive database credentials from a properties file outside of the war, specifically
 * in CATALINA_HOME/conf/overrides/schultzco-web-services.properties
 *
 * Related: http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
 *
 * From: http://stackoverflow.com/questions/4884704/referencing-configuration-properties-outside-of-the-war
 * Similar to:
 <context:property-placeholder
     location="file:${catalina.home}/webapps/datasource.properties"
     ignore-unresolvable="true"/>
 *
 * Ignore resource not found so dev environments work.
 */
@Configuration
@PropertySources({
          @PropertySource(name = "SensitiveDatumsDefault",  value = "classpath:schultzco-web-services.properties")
        , @PropertySource(name = "SensitiveDatumsOverride", value = "file://${catalina.home}/conf/overrides/schultzco-web-services.properties", ignoreResourceNotFound = true)
})
public class SensitiveDatumsConfiguration {
}
