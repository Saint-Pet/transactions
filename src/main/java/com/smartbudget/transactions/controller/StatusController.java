package com.smartbudget.transactions.controller;


import com.smartbudget.transactions.model.Status;
import com.smartbudget.transactions.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @GetMapping
    public List<Status> getAllStatuses(){
        return statusService.getAllStatuses();
    }

    @GetMapping("/{statusId}")
    public Optional<Status> getStatusById(@PathVariable Integer statusId){
        return statusService.getStatusById(statusId);
    }

    @PostMapping
    public void createNewStatus(@RequestParam String status_name,
                                @RequestParam String description){
        Status status = new Status();
        status.setStatusName(status_name);
        status.setDescription(description);
        statusService.createNewStatus(status);
    }

    @PutMapping("/{statusId}")
    public ResponseEntity<Status> updateStatus(@PathVariable Integer statusId,
                                       @RequestParam String status_name,
                                       @RequestParam String description){
        try {
            Status updatedStatus = statusService.updateStatus(statusId, status_name, description);
            return ResponseEntity.ok(updatedStatus);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{statusId}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Integer statusId){
        try {
            statusService.deleteStatus(statusId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
