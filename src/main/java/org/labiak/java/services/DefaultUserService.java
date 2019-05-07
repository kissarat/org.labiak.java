package org.labiak.java.services;

import org.labiak.java.config.AppConfig;
import org.labiak.java.entities.User;
import org.labiak.java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private AppConfig config;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public User create(User user) {
        user.setPassword(config.encoder().encode(user.getPassword()));
        return repository.save(user);
    }
}
