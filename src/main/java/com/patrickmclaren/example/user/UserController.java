package com.patrickmclaren.example.user;

import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> users() {
        return userRepository.findAll();
    }

    // TODO(patrick): How to test that RequestMapping value matches
    //   `getUser` parameter name?
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId) {
        User user = userRepository.findOne(userId);
        if (Optional.fromNullable(user).isPresent()) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid User user) {
        userRepository.save(user);
    }

    // TODO(patrick): Investigate using RequestMethod.PUT
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public void updateUser(@PathVariable Long userId, @Valid User newUser) {
        User user = userRepository.findOne(userId);
        if (!Optional.fromNullable(user).isPresent()) {
            throw new UserNotFoundException();
        }

        // TODO(patrick): Investigate more idiomatic method to update
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());

        userRepository.save(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long userId) {
        User user = userRepository.findOne(userId);
        if (!Optional.fromNullable(user).isPresent()) {
            throw new UserNotFoundException();
        }

        userRepository.delete(user);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such User")
    public class UserNotFoundException extends RuntimeException {
    }
}
