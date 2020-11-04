package com.home.repository;

import com.home.entity.User;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Data
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public Optional<User> findById(long userId) {
        return users.stream().filter(p -> p.getId() == userId).findFirst();
    }
}
