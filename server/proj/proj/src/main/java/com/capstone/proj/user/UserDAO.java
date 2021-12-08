package com.capstone.proj.user;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    public int createUser(User user);

    public List<User> getAllUsers();
    public Optional<User> getUserById(int id);

    public int updateUser(int id, User user);

    public int deleteUser(int id);

    // || ===================  Login Authentication ===================== ||

    public Optional<User> authenticateLogin(String email, String password);
    public int updateUserToken(User user);
    public Optional<User> findByToken(String token);

}
