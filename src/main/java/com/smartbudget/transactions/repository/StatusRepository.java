package com.smartbudget.transactions.repository;

import com.smartbudget.transactions.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {}
