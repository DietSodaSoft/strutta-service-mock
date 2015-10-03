package com.schultzco.webservices.api.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

/**
 * Created by wendel.schultz on 9/26/15.
 */
@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {
        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(source);
        } catch (DateTimeParseException e) {
            ldt = ZonedDateTime.parse(source).toLocalDateTime();;
        }

        return ldt;

    }
}
