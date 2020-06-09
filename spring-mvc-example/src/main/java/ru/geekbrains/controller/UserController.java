package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.service.UserService;

import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model,
                           @RequestParam(name = "minAge", required = false) Integer minAge,
                           @RequestParam(name = "maxAge", required = false) Integer maxAge,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        logger.info("User list. With minAge = {} and maxAge = {}", minAge, maxAge);

        Page<User> userPage = userService.filterByAge(minAge, maxAge, username,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("usersPage", userPage);
        model.addAttribute("prevPageNumber", userPage.hasPrevious() ? userPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", userPage.hasNext() ? userPage.nextPageable().getPageNumber() + 1 : -1);
        return "users";
    }

    @GetMapping("new")
    public String createUser(Model model) {
        logger.info("Create user form");

        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        logger.info("Save user method");

        if (bindingResult.hasErrors()) {
            return "user";
        }
        logger.info("password {} repeat {}", user.getPassword(), user.getRepeatPassword());
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword", "", "пароли не совпадают");
            return "user";
        }

        userService.save(user);
        return "redirect:/user";
    }
}
