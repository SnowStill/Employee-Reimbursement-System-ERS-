package com.revature.services;

import com.revature.DAOs.ReimDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingReimDTO;
import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingReimDTO;
import com.revature.models.Reim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReimService {

    private ReimDAO reimDao;
    private UserDAO userDao;

    private IncomingReimDTO reimDTO;

    @Autowired
    public ReimService(ReimDAO reimDao, UserDAO userDao) {
        this.reimDao = reimDao;
        this.userDao = userDao;
    }
    //need to test this with invalid user id
    public void createReimbursement(IncomingReimDTO reimDTO) throws IllegalArgumentException {
        reimDao.save(new Reim(reimDTO.getDescription(), reimDTO.getAmount(), reimDTO.getStatus(), userDao.findById(reimDTO.getUserId()).get()));
    }

    public List<OutgoingReimDTO> getAllReimbursements() {
        List<Reim> reims = reimDao.findAll();
        List<OutgoingReimDTO> outReims = new ArrayList<OutgoingReimDTO>();

        //convert regular Reimbursement objects to OutgoingReimDTO objects
        for (Reim reim : reims) {
            OutgoingReimDTO outR = new OutgoingReimDTO(reim.getReimbId(), reim.getDescription(), reim.getAmount(), reim.getStatus(), reim.getUser().getUserId());
            outReims.add(outR);
        }
        return outReims;
    }

    public List<OutgoingReimDTO> getReimbursementsByUserId(int userId) {
        List<Reim> reims = reimDao.findByUserUserId(userId);
        List<OutgoingReimDTO> outReims = new ArrayList<OutgoingReimDTO>();

        //convert regular Reimbursement objects to OutgoingReimDTO objects
        for (Reim reim : reims) {
            OutgoingReimDTO outR = new OutgoingReimDTO(reim.getReimbId(), reim.getDescription(), reim.getAmount(), reim.getStatus(), reim.getUser().getUserId());
            outReims.add(outR);
        }

        return outReims;
    }

    public void updateReimbursementStatus(int reimId, String status) {
        Reim reim = reimDao.findById(reimId).get();
        reim.setStatus(status);
        reimDao.save(reim);
    }

    public List<OutgoingReimDTO> getReimbursementsByStatus(String status) {
        List<Reim> reims = reimDao.findByStatus(status);
        List<OutgoingReimDTO> outReims = new ArrayList<OutgoingReimDTO>();
        //convert regular Reimbursement objects to OutgoingReimDTO objects
        for (Reim reim : reims) {
            OutgoingReimDTO outR = new OutgoingReimDTO(reim.getReimbId(), reim.getDescription(), reim.getAmount(), reim.getStatus(), reim.getUser().getUserId());
            outReims.add(outR);
        }
        return outReims;
    }

    public List<OutgoingReimDTO> getReimbursementsByStatusAndUserId(String status, int userId) {
        List<Reim> reims = reimDao.findByStatusAndUserUserId(status, userId);
        List<OutgoingReimDTO> outReims = new ArrayList<OutgoingReimDTO>();

        //convert regular Reimbursement objects to OutgoingReimDTO objects
        for (Reim reim : reims) {
            OutgoingReimDTO outR = new OutgoingReimDTO(reim.getReimbId(), reim.getDescription(), reim.getAmount(), reim.getStatus(), reim.getUser().getUserId());
            outReims.add(outR);
        }

        return outReims;
    }
}