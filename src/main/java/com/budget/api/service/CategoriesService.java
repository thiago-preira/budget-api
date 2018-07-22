package com.budget.api.service;

import com.budget.api.entity.Category;
import com.budget.api.exceptions.ResourceNotFoundException;
import com.budget.api.repository.CategoriesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

  private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category with id %d not found";
  private CategoriesRepository categoriesRepository;

  @Autowired
  public CategoriesService(CategoriesRepository categoriesRepository) {
    this.categoriesRepository = categoriesRepository;
  }

  public List<Category> findAll() {
    return categoriesRepository.findAll();
  }

  public Category findById(Long id) {
    Optional<Category> optionalCategory = categoriesRepository.findById(id);
    return optionalCategory.orElseThrow(
        () -> new ResourceNotFoundException(String.format(CATEGORY_NOT_FOUND_MESSAGE, id)));
  }

  public Category save(Category category) {
    return categoriesRepository.save(category);
  }

  public void delete(Long id) {
    categoriesRepository.deleteById(id);
  }
}
