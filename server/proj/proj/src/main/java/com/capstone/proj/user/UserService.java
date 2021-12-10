package com.capstone.proj.user;

import com.capstone.proj.constituency.Constituency;
import com.capstone.proj.constituency.ConstituencyService;
import com.capstone.proj.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserDAO userDAO;
    private ConstituencyService constituencyService;

    @Autowired
    public UserService(@Qualifier("postgresUser") UserDAO userDAO, ConstituencyService constituencyService) {
        this.userDAO = userDAO;
        this.constituencyService = constituencyService;
    }

    public int createUser(User user) {
        Constituency constituency = constituencyService.getConstituencyFromPostcode(user.getPostcode());
        Integer constituency_id = constituency.getConstituency_id();
        user.setConstituencyId(constituency_id);
        user.setPostcode(null);
        return userDAO.createUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public Optional<User> getUserById(int id) {
        Optional<User> user = userDAO.getUserById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFound("User with id: " + id + " not found");
        }
        return user;
    }

    public int updateUser(int id, User user) {
        Optional<User> oldUser = userDAO.getUserById(id);
        if (oldUser.isEmpty()) {
            throw new ResourceNotFound("User with id: " + id + " not found");
        }
        if (user.getPostcode() != null) {
            Constituency constituency = constituencyService.getConstituencyFromPostcode(user.getPostcode());
            Integer constituency_id = constituency.getConstituency_id();
            user.setConstituencyId(constituency_id);
            user.setPostcode(null);
        }
        return userDAO.updateUser(id, user);
    }

    public int deleteUser(int id) {
        Optional<User> user = userDAO.getUserById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFound("User with id: " + id + " not found");
        }
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
        throw new ResourceNotFound("No user with token");
    }


    // todo: see if this is the logic we want
    public int removeTokenOnLogOut(String token) {
        return userDAO.removeTokenOnLogOut(token);
    }
}
