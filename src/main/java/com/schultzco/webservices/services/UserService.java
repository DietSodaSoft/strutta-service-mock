package com.schultzco.webservices.services;

import com.google.common.base.Preconditions;
import com.schultzco.webservices.models.SocialUser;
import com.schultzco.webservices.models.User;
import com.schultzco.webservices.services.daos.SocialUserRepository;
import com.schultzco.webservices.services.daos.UserRepository;
import com.schultzco.webservices.utils.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by wendel.schultz on 8/25/15.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private UuidGenerator uuidGenerator;

    public User findByUuid(UUID uuid) {
        Preconditions.checkNotNull(uuid, "must provide uuid");

        return userRepository.findByUuid(uuid);
    }

    public User findBySocialUserTypeId(String typeId) {
        Preconditions.checkNotNull(typeId, "must provide typeId");

        return userRepository.findBySocialUserTypeId(typeId);
    }

    public User save(User unsaved) {
        Preconditions.checkNotNull(unsaved);

        if(unsaved.getUuid() == null){
            unsaved.setUuid(uuidGenerator.createUuid());
        }

        final User saved = userRepository.save(unsaved);

        for (SocialUser socialUser : unsaved.getSocialUsers()) {
            if(socialUser.getUuid() == null){
                socialUser.setUuid(uuidGenerator.createUuid());
                socialUser.setUser(saved);
                socialUserRepository.save(socialUser);
            }
        }

        return saved;
    }
}
