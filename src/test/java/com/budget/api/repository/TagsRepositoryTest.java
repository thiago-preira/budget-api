package com.budget.api.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.budget.api.TestHelper;
import com.budget.api.entity.Category;
import com.budget.api.entity.Tag;
import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class TagsRepositoryTest {

  @Autowired
  TagsRepository tagsRepository;

  @Autowired
  CategoriesRepository categoriesRepository;

  Category savedCategory;

  @Before
  public void init(){
    savedCategory = categoriesRepository.save(TestHelper.getCategory());
  }

  @Test
  public void mustSaveTag() {
    Tag Tag = TestHelper.getTag(savedCategory);

    List<Tag> TagList = tagsRepository.findAll();
    assertThat(TagList, Matchers.hasSize(0));

    Tag savedTag = tagsRepository.save(Tag);

    assertNotNull(savedTag);
    assertNotNull(savedTag.getId());

    TagList = tagsRepository.findAll();
    assertThat(TagList, Matchers.hasSize(1));
  }

  @Test
  public void mustDeleteTag() {
    Tag Tag = TestHelper.getTag(savedCategory);
    Tag savedTag = tagsRepository.save(Tag);

    tagsRepository.deleteById(savedTag.getId());

    List<Tag> TagList = tagsRepository.findAll();
    assertThat(TagList, Matchers.hasSize(0));
  }

  @Test
  public void mustFindTagById() {
    Tag Tag = TestHelper.getTag(savedCategory);
    Tag savedTag = tagsRepository.save(Tag);

    Optional<Tag> optionalTag = tagsRepository.findById(savedTag.getId());

    assertTrue(optionalTag.isPresent());
    assertTrue(optionalTag.get().getName().equals(savedTag.getName()));

  }
  
}
