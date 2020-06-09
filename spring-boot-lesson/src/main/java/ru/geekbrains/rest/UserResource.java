package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/v1/user")
@RestController
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}/id")
    public User findById(@PathVariable("id") long id) {
        return userService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Id found in the create request");
        }
        userService.save(user);
        return user;
    }

    @PutMapping
    public void updatePerson(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping(path = "/{id}/id")
    public void deletePerson(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException exception) {
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
