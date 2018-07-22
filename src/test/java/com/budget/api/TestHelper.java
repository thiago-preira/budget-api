package com.budget.api;

import com.budget.api.entity.Category;
import com.budget.api.entity.Tag;
import java.util.Arrays;
import java.util.List;

public abstract class TestHelper {

  public static Category getCategory() {
    Category category = new Category();
    category.setName("Test");
    return category;
  }

  public static List<Category> getCategoryList() {
    Category category = getSavedCategory();
    return Arrays.asList(category);
  }

  public static Category getSavedCategory() {
    Category category = new Category();
    category.setId(1L);
    category.setName("Test");
    return category;
  }

  public static Tag getTag(Category savedCategory) {
    Tag tag = new Tag();
    tag.setName("Test");
    tag.setCategory(savedCategory);
    return tag;
  }

  public static Tag getSavedTag(Category savedCategory) {
    Tag tag = new Tag();
    tag.setId(1L);
    tag.setName("Test");
    tag.setCategory(savedCategory);
    return tag;
  }



}
