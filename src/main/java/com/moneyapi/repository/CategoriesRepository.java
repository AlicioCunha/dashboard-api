package com.moneyapi.repository;

import com.moneyapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {


}
