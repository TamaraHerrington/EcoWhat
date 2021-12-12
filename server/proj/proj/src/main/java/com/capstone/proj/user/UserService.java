package com.capstone.proj.user;

import com.capstone.proj.constituency.Constituency;
import com.capstone.proj.constituency.ConstituencyService;
import com.capstone.proj.exception.BadRequest;
import com.capstone.proj.exception.ResourceNotFound;
import com.capstone.proj.token.Token;
import com.capstone.proj.token.TokenService;
import com.capstone.proj.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserDAO userDAO;
    private TokenService tokenService;
    private ConstituencyService constituencyService;
    private Validator validator;

    @Autowired
    public UserService(@Qualifier("postgresUser") UserDAO userDAO, TokenService tokenService, ConstituencyService constituencyService, Validator validator) {
        this.userDAO = userDAO;
        this.tokenService = tokenService;
        this.constituencyService = constituencyService;
        this.validator = validator;
    }

    public int createUser(User user) {
        // initially set these to null
        user.setConstituencyId(null);
        user.setConstituencyName(null);
        user.setToken(null);

        // first name
        if (user.getFirstName() == null || user.getFirstName().length() == 0) {
            throw new BadRequest("First name cannot be empty");
        }

        // last name
        if (user.getLastName() == null || user.getLastName().length() == 0) {
            throw new BadRequest("Last name cannot be empty");
        }

        // email
        boolean isEmailValid = validator.validateEmail(user.getEmail());
        if (!isEmailValid) {
            throw new BadRequest("Invalid email address");
        }
        Optional<User> emailUser = userDAO.getUserByEmail(user.getEmail());
        if (emailUser.isPresent()) {
            throw new BadRequest("User with email already exists");
        }

        // password
        boolean isPasswordValid = validator.validatePassword(user.getPassword());
        if (!isPasswordValid) {
            throw new BadRequest("Invalid password");
        }

        // constituency
        if (user.getPostcode() == null || user.getPostcode().length() == 0) {
            throw new BadRequest("Postcode cannot be empty");
        }
        Constituency constituency;
        try {
            constituency = constituencyService.getConstituencyFromPostcode(user.getPostcode());
        } catch (Exception e) {
            throw new BadRequest(user.getPostcode() + " is not a valid postcode");
        }
        Integer constituency_id = constituency.getConstituency_id();
        String constituency_name = constituency.getConstituency_name();
        user.setConstituencyId(constituency_id);
        user.setConstituencyName(constituency_name);
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
        // if id doesn't exist, create new user
        Optional<User> oldUser = userDAO.getUserById(id);
        if (oldUser.isEmpty()) {
            createUser(user);
        }

        // initially set these as null
        user.setConstituencyId(null);
        user.setConstituencyName(null);
        user.setToken(null);

        // first name
        if (user.getFirstName() == null || user.getFirstName().length() == 0) {
            throw new BadRequest("First name cannot be empty");
        }

        // last name
        if (user.getLastName() == null || user.getLastName().length() == 0) {
            throw new BadRequest("Last name cannot be empty");
        }

        // email
        boolean isEmailValid = validator.validateEmail(user.getEmail());
        if (!isEmailValid) {
            throw new BadRequest("Invalid email address");
        }
        Optional<User> emailUser = userDAO.getUserByEmail(user.getEmail());
        if (emailUser.isPresent() && emailUser.get().getId() != id) {
            throw new BadRequest("User with email already exists");
        }

        // password
        boolean isPasswordValid = validator.validatePassword(user.getPassword());
        if (!isPasswordValid) {
            throw new BadRequest("Invalid password");
        }

        // constituency
        if (user.getPostcode() == null || user.getPostcode().length() == 0) {
            throw new BadRequest("Postcode cannot be empty");
        }
        Constituency constituency;
        try {
            constituency = constituencyService.getConstituencyFromPostcode(user.getPostcode());
        } catch (Exception e) {
            throw new BadRequest(user.getPostcode() + " is not a valid postcode");
        }
        Integer constituency_id = constituency.getConstituency_id();
        String constituency_name = constituency.getConstituency_name();
        user.setConstituencyId(constituency_id);
        user.setConstituencyName(constituency_name);
        user.setPostcode(null);

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

    public Token authenticateLogin(String email, String password) {
        // check if user with email exists
        Optional<User> emailUser = userDAO.getUserByEmail(email);
        if (emailUser.isEmpty()) {
            throw new BadRequest("No user with this email exists");
        }

        // authenticate with password
        Optional<User> user = userDAO.authenticateLogin(email, password);
        if (user.isEmpty()) {
            throw new BadRequest("Incorrect password");
        }
        // generate token
        Token token = new Token(
                user.get().getId(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                "Secret Key");
        return token;
    }

    public int logOut(Token token) {
        // validate token

        // blacklist token
        return tokenService.blackListToken(token);
    }

    // old methods that don't coincide with token based authentication
//    public Optional<User> findByToken(String token) {
//        Optional<User> user = userDAO.findByToken(token);
//        if (user.isPresent()) {
//            return user;
//        }
//        throw new ResourceNotFound("No user with token");
//    }
//
//
//    // todo: see if this is the logic we want
//    public int removeTokenOnLogOut(String token) {
//        return userDAO.removeTokenOnLogOut(token);
//    }
}
