package com.moneyapi.repository;

import com.moneyapi.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriesRepository extends PagingAndSortingRepository<Category, Long> {


}
