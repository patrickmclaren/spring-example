package com.patrickmclaren.example.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class UserTest {
    private User user;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testNewUser() {
        long id = 1;
        String firstName = "Joe";
        String lastName = "Bloggs";

        user = new User();

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
    }
}
