package com.capstone.proj.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("postgresUser") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int createUser(User user) {
        return userDAO.createUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public Optional<User> getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public int updateUser(int id, User user) {
        return userDAO.updateUser(id, user);
    }

    public int deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    // || ===================  Login Authentication ===================== ||

    public String authenticateLogin(String email, String password) {

        Optional<User> userOptional = userDAO.authenticateLogin(email, password);
        if(userOptional.isPresent()){
            String token = UUID.randomUUID().toString();
            User user = userOptional.get();
            user.setToken(token);
            userDAO.updateUserToken(user);
            return token;
        }
        return "";
    }

    public Optional<User> findByToken(String token) {
        Optional<User> user = userDAO.findByToken(token);
        if (user.isPresent()) {
            return user;
        }
        throw new IllegalStateException("No user with token");
    }

    public int removeTokenOnLogOut(String token) {
        return userDAO.removeTokenOnLogOut(token);
    }
}
