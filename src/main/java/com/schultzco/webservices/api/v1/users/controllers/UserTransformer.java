package com.schultzco.webservices.api.v1.users.controllers;

import com.google.common.base.Preconditions;
import com.schultzco.webservices.api.v1.users.dtos.UserDTO;
import com.schultzco.webservices.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wendel.schultz on 8/25/15.
 */
@Component
public class UserTransformer {

    public UserDTO toDtoView(User user) {
        Preconditions.checkNotNull(user);

        UserDTO response = new UserDTO();

        response.uuid = user.getUuid();
        response.emailAddress = user.getEmailAddress();
        response.firstName = user.getFirstName();
        response.lastName = user.getLastName();

        return response;
    }

    User toSavedUser(UserDTO dto) {
        Preconditions.checkNotNull(dto);

        User user = new User();

        user.setEmailAddress(dto.emailAddress);
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setUuid(dto.uuid);

        return user;
    }

}
