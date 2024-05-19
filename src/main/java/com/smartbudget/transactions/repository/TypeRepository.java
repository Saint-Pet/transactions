package com.smartbudget.transactions.repository;

import com.smartbudget.transactions.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {}
