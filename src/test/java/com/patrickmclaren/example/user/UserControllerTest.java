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

    public User makeUser() {
        User newUser = new User();
        newUser.setFirstName("Bruce");
        newUser.setLastName("Wayne");

        return newUser;
    }

    @Test
    public void testCreateUser() throws Exception {
        User newUser = makeUser();

        userController.createUser(newUser);
        verify(userRepository).save(eq(newUser));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User userDetails = makeUser();

        userController.updateUser(Long.valueOf(0), userDetails);
        verify(userRepository).save(eq(user));

        assertEquals(userDetails.getFirstName(), user.getFirstName());
        assertEquals(userDetails.getLastName(), user.getLastName());
    }

    @Test
    public void testDeleteUser() throws Exception {
        userController.deleteUser(Long.valueOf(0));
        verify(userRepository).delete(any(User.class));
    }
}