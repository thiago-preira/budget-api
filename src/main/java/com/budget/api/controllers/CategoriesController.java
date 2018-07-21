package com.budget.api.controllers;

import com.budget.api.entity.Category;
import com.budget.api.service.CategoriesService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

  private CategoriesService categoriesService;

  @Autowired
  public CategoriesController(CategoriesService categoriesService) {
    this.categoriesService = categoriesService;
  }

  @GetMapping
  public List<Category> findAll() {
    return categoriesService.findAll();
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable("id") Long id) {
    return categoriesService.findById(id);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Category category) {
    Category savedCategory = categoriesService.save(category);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedCategory.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    categoriesService.delete(id);
  }
}
