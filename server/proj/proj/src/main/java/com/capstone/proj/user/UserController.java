package com.capstone.proj.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    // || ===================  Login Authentication ===================== ||

    @PostMapping("token")
    public String authenticateLogin(
            @RequestParam("email") final String email,
            @RequestParam("password") final String password) {
                return userService.authenticateLogin(email, password);
    }

//    @GetMapping("token")
//    public String getToken(@RequestParam("email") final String email, @RequestParam("password") final String password) {
//
//        return userService.findByToken();
//    }

}
