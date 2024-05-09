package com.revature.DAOs;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.Reim;
import java.util.List;

@Repository
public interface ReimDAO extends JpaRepository<Reim, Integer>{
    public List<Reim> findByUserUserId(int userId);
    public List<Reim> findAll();
    public List<Reim> findByStatus(String status);
    public List<Reim> findByStatusAndUserUserId(String status, int userId);
}
