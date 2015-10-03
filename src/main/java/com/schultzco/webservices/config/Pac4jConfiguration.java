package com.schultzco.webservices.config;

import com.google.common.collect.Lists;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.oauth.client.FacebookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class Pac4jConfiguration {

    private static final String PROP_FACEBOOK_APP_ID = "facebook.app.id";
    private static final String PROP_FACEBOOK_APP_SECRET = "facebook.app.secret";
    private static final String PROP_AUTHORIZATION_CALLBACK_URL = "authorization.callback.url";

    private String facebookKey;

    private String facebookSecret;

    private String callbackUrl;

    @Autowired
    private Environment environment;

    @PostConstruct
    void init() {
        facebookKey = environment.getProperty(PROP_FACEBOOK_APP_ID);
        facebookSecret = environment.getProperty(PROP_FACEBOOK_APP_SECRET);

        callbackUrl = environment.getProperty(PROP_AUTHORIZATION_CALLBACK_URL);
        if(callbackUrl == null){
            throw new IllegalStateException("Must provide authorization callback for pac4j using property " + PROP_AUTHORIZATION_CALLBACK_URL);
        }
    }

    @Bean(initMethod = "init")
    public Clients clients() {
        List<Client> clients = Lists.newLinkedList();
        Client fb = facebookClient();
        if(fb != null) {
            clients.add(fb);
        }

        return new Clients(callbackUrl, clients);
    }

    @Bean
    public FacebookClient facebookClient() {
        final FacebookClient facebookClient;
        if(facebookKey != null && facebookSecret != null) {
            facebookClient = new FacebookClient(facebookKey, facebookSecret);
            facebookClient.setScope("public_profile,email,user_friends");
        } else {
            facebookClient = null;
        }

        return facebookClient;
    }
}