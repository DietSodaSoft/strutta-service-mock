package com.schultzco.webservices.api.v1.users.controllers;

import com.schultzco.webservices.api.exceptions.BadRequestException;
import com.schultzco.webservices.api.exceptions.NotFoundException;
import com.schultzco.webservices.api.v1.users.dtos.UserDTO;
import com.schultzco.webservices.models.User;
import com.schultzco.webservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by wendel.schultz on 8/25/15.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTransformer userTransformer;

    @RequestMapping(value = "/api/v1/users/{uuid}", method = RequestMethod.GET)
    public UserDTO findUserByUuid(@PathVariable(value = "uuid") UUID uuid) {

        if(uuid == null){
            throw new BadRequestException("Must provide user UUID");
        }

        final User user = userService.findByUuid(uuid);

        if(user == null){
            throw new NotFoundException("User for UUID '" + uuid + "' doesn't exist");
        }

        return userTransformer.toDtoView(user);
    }

    @RequestMapping(value = "/api/v1/users", method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody @Validated UserDTO dto) {

        if(dto == null) {
            throw new BadRequestException("Must provide User");
        }

        final User unsaved = userTransformer.toSavedUser(dto);

        final User saved;
        try {
            saved = userService.save(unsaved);
        } catch( DataIntegrityViolationException ive) {
            throw new BadRequestException(ive.getLocalizedMessage());
        }

        return userTransformer.toDtoView(saved);
    }


}
