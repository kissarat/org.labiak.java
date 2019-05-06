package org.labiak.java.repositories;

import org.labiak.java.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
