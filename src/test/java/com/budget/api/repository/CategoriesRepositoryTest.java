package com.budget.api.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.budget.api.TestHelper;
import com.budget.api.entity.Category;
import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CategoriesRepositoryTest {

  @Autowired
  CategoriesRepository categoriesRepository;

  @Test
  public void mustSaveCategory() {
    Category category = TestHelper.getCategory();

    List<Category> categoryList = categoriesRepository.findAll();
    assertThat(categoryList, Matchers.hasSize(0));

    Category savedCategory = categoriesRepository.save(category);

    assertNotNull(savedCategory);
    assertNotNull(savedCategory.getId());

    categoryList = categoriesRepository.findAll();
    assertThat(categoryList, Matchers.hasSize(1));
  }

  @Test
  public void mustDeleteCategory() {
    Category category = TestHelper.getCategory();
    Category savedCategory = categoriesRepository.save(category);

    categoriesRepository.deleteById(savedCategory.getId());

    List<Category> categoryList = categoriesRepository.findAll();
    assertThat(categoryList, Matchers.hasSize(0));
  }

  @Test
  public void mustFindCategoryById() {
    Category category = TestHelper.getCategory();
    Category savedCategory = categoriesRepository.save(category);

    Optional<Category> optionalCategory = categoriesRepository.findById(savedCategory.getId());

    assertTrue(optionalCategory.isPresent());
    assertTrue(optionalCategory.get().getName().equals(savedCategory.getName()));

  }



}
