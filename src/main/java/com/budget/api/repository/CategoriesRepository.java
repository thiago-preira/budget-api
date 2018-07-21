package com.budget.api.repository;

import com.budget.api.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

  @Override
  @EntityGraph(value = "Category.detail", type = EntityGraphType.LOAD)
  Optional<Category> findById(Long aLong);
}
