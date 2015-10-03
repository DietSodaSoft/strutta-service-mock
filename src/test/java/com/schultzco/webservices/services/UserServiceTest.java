package com.schultzco.webservices.services;

import com.schultzco.webservices.models.User;
import com.schultzco.webservices.services.daos.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by wendel.schultz on 8/25/15.
 */
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void before() {
        userService = new UserService();
        userRepository = null;

        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NullPointerException.class)
    public void testNullUuidThrowsException() {
        userService.findByUuid(null);
    }

    @Test
    public void testUserServiceDelegatesToRepository() {
        User user = mock(User.class);
        UUID id = UUID.randomUUID();

        doReturn(user).when(userRepository).findByUuid(eq(id));

        final User found = userService.findByUuid(id);

        verify(userRepository, times(1)).findByUuid(id);
        assertEquals("wrong user found", user, found);

    }

}
