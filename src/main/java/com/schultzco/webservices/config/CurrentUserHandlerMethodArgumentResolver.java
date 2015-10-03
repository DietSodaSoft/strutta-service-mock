package com.schultzco.webservices.config;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.springframework.security.authentication.ClientAuthenticationToken;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(CurrentUser.class) != null
                && methodParameter.getParameterType().equals(CommonProfile.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        if (this.supportsParameter(methodParameter)) {
            Principal principal = (Principal) webRequest.getUserPrincipal();
            return (CommonProfile) ((ClientAuthenticationToken) principal).getUserProfile();
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}
