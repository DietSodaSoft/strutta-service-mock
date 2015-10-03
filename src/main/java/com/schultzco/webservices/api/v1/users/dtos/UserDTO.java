package com.schultzco.webservices.api.v1.users.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * DTOs aren't always this closely tied to the DB model. Nor are the create models the same as the view model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    public UUID uuid;

    @NotNull
    public String firstName;

    @NotNull
    public String lastName;

    @NotNull
    public String emailAddress;

}
