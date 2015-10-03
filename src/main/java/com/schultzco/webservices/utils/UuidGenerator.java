package com.schultzco.webservices.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by wendel.schultz on 8/26/15.
 */
@Component
public class UuidGenerator {

    public UUID createUuid(){
        return UUID.randomUUID();
    }

}
