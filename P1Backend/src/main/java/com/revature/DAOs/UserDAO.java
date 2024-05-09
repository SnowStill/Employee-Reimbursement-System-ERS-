package com.revature.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.models.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
    public Optional<User> findByUsernameAndPassword(String username, String password);
    public Optional<User> findById(int id);
    public List<User> findAll();
    public void deleteById(int id);
    public Optional<User> getByUsername(String username);
}
