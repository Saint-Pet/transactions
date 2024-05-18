package com.transaction.service;

import com.transaction.models.Status;
import com.transaction.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatuses(){
        return statusRepository.findAll();
    }

    public Optional<Status> getStatusById(Integer id) {
        return statusRepository.findById(id);
    }

    public void createNewStatus(Status status) {
        statusRepository.save(status);
    }

    public Status updateStatus(Integer statusId, String status_name, String description) {
        Optional<Status> status = statusRepository.findById(statusId);
        if (status.isPresent()){
            Status updatedStatus = status.get();
            updatedStatus.setStatusName(status_name);
            updatedStatus.setDescription(description);
            return updatedStatus;
        } else {
            throw new IllegalArgumentException("Invalid status_id");}
    }

    public void deleteStatus(Integer statusId) {
        statusRepository.deleteById(statusId);
    }


}
