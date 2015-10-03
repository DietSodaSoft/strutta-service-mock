package com.schultzco.webservices.config;

import com.google.common.collect.Sets;
import com.schultzco.webservices.models.SocialUser;
import com.schultzco.webservices.models.User;
import com.schultzco.webservices.services.UserService;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.springframework.security.authentication.ClientAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Primary
public class CustomUserDetailsService implements AuthenticationUserDetailsService<ClientAuthenticationToken>  {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserDetails(ClientAuthenticationToken clientAuthenticationToken) throws UsernameNotFoundException {
        UserProfile userProfile = clientAuthenticationToken.getUserProfile();

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);

        final CommonProfile commonProfile = (CommonProfile) userProfile;

        final String typedId = commonProfile.getTypedId();

        User user = userService.findBySocialUserTypeId(typedId);

        if (user == null) {
            user = new User();
            user.setEmailAddress(commonProfile.getEmail());
            user.setFirstName(commonProfile.getFirstName());
            user.setLastName(commonProfile.getFamilyName());
            final SocialUser socialUser = new SocialUser();
            socialUser.setTypeId(typedId);
            socialUser.setPictureUrl(commonProfile.getPictureUrl());
            socialUser.setUser(user);

            final Set<SocialUser> socialUsers = Sets.newHashSet(socialUser);

            user.setSocialUsers(socialUsers);

            userService.save(user);
        }

        return new org.springframework.security.core.userdetails.User(user.getUuid().toString(), "password", grantedAuthorities);
    }

    /**
     * Pull the user out of the SecurityContextHolder.  Allows for mockability.
     */
    public UserDetails getSecurityContextUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

    }

}
