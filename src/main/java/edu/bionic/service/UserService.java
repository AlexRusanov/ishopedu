package edu.bionic.service;

import edu.bionic.domain.User;

import java.util.Optional;

public interface UserService {

    User registerNewUser(User user);

    Optional<User> getAuthenticatedUser();
}
