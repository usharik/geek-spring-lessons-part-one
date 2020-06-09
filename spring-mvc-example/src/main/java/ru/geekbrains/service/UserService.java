package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.persist.repo.UserRepository;
import ru.geekbrains.persist.repo.UserSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    public Page<User> filterByAge(Integer minAge, Integer maxAge, String username, Pageable pageable) {
        Specification<User> specification = UserSpecification.trueLiteral();

        if (minAge != null) {
            specification = specification.and(UserSpecification.ageGreaterThanOrEqual(minAge));
        }
        if (maxAge != null) {
            specification = specification.and(UserSpecification.ageLessThanOrEqual(maxAge));
        }
        if (username != null && !username.isEmpty()) {
            specification = specification.and(UserSpecification.findByUsername(username));
        }
        return repository.findAll(specification, pageable);
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }
}
