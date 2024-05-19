package com.smartbudget.transactions.repository;

import com.smartbudget.transactions.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
