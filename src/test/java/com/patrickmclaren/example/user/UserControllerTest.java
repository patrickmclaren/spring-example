package com.patrickmclaren.example.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserRepository userRepository;

    private User user;
    private ArrayList<User> users;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setFirstName("Foo");
        user.setLastName("Bar");

        users = new ArrayList<>(0);
        users.add(user);

        when(userRepository.findOne(anyLong())).thenReturn(user);

        userController = new UserController();
        userController.setUserRepository(userRepository);
    }

    @After
    public void tearDown() {
        user = null;
        users = null;
        userController = null;
        userRepository = null;
    }

    @Test
    public void testUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users, userController.users());
    }

    @Test
    public void testGetUser() throws Exception {
        assertEquals(user, userController.getUser(Long.valueOf(0)));
    }

    @Test
    public void testCreateUser() throws Exception {
        // TODO(patrick): Implement this
    }

    @Test
    public void testUpdateUser() throws Exception {
        // TODO(patrick): Implement this
    }

    @Test
    public void testDeleteUser() throws Exception {
        userController.deleteUser(Long.valueOf(0));
        verify(userRepository).delete(any(User.class));
    }
}