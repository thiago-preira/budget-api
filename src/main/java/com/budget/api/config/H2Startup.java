package com.budget.api.config;

import com.budget.api.entity.Category;
import com.budget.api.entity.Tag;
import com.budget.api.repository.CategoriesRepository;
import com.budget.api.repository.TagsRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class H2Startup  implements InitializingBean {

  private CategoriesRepository categoriesRepository;
  private TagsRepository tagsRepository;

  @Autowired
  public H2Startup(CategoriesRepository categoriesRepository, TagsRepository tagsRepository) {
    this.categoriesRepository = categoriesRepository;
    this.tagsRepository = tagsRepository;
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    Category category = new Category();
    category.setName("HABITAÇÃO");
    category.setIcon("home");
    categoriesRepository.save(category);

    Category category1 = new Category();
    category1.setName("SAÚDE");
    category1.setIcon("healing");
    categoriesRepository.save(category1);

    Category category2 = new Category();
    category2.setName("TRANSPORTE");
    category2.setIcon("directions_car");
    categoriesRepository.save(category2);

    Category category3 = new Category();
    category3.setName("PESSOAL");
    category3.setIcon("face");
    categoriesRepository.save(category3);

    Category category4 = new Category();
    category4.setName("EDUCAÇÃO");
    category4.setIcon("school");
    categoriesRepository.save(category4);

    Category category5 = new Category();
    category5.setName("OUTROS");
    category5.setIcon("layers");
    categoriesRepository.save(category5);

    Category category6 = new Category();
    category6.setName("RECEITA");
    category6.setIcon("euro_symbol");
    categoriesRepository.save(category6);

    Category category7 = new Category();
    category7.setName("LAZER");
    category7.setIcon("beach_access");
    categoriesRepository.save(category7);

    Tag tag = new Tag();
    tag.setName("LUZ");
    tag.setCategory(category);
    tagsRepository.save(tag);

    Tag tag1 = new Tag();
    tag1.setName("TELEFONE");
    tag1.setCategory(category);
    tagsRepository.save(tag1);

    Tag tag2 = new Tag();
    tag2.setName("ALUGUEL");
    tag2.setCategory(category);
    tagsRepository.save(tag2);

    Tag tag3 = new Tag();
    tag3.setName("INTERNET");
    tag3.setCategory(category);
    tagsRepository.save(tag3);

    Tag tag4 = new Tag();
    tag4.setName("MERCADO");
    tag4.setCategory(category);
    tagsRepository.save(tag4);

  }
}
