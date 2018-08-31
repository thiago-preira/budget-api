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

    tagsRepository.save(new Tag("LUZ",category));
    tagsRepository.save( new Tag("TELEFONE",category));
    tagsRepository.save( new Tag("ALUGUEL",category));
    tagsRepository.save( new Tag("INTERNET",category));
    tagsRepository.save( new Tag("MERCADO",category));
    tagsRepository.save( new Tag("ALUGUEL",category));
    tagsRepository.save( new Tag("TV",category));

    tagsRepository.save( new Tag("MEDICAMENTOS",category1));
    tagsRepository.save( new Tag("CONSULTA",category1));
    tagsRepository.save( new Tag("TRATAMENTOS",category1));

    tagsRepository.save( new Tag("ESTACIONAMENTOS",category2));
    tagsRepository.save( new Tag("ALUGUEL DE VEICULO",category2));
    tagsRepository.save( new Tag("SEGURO",category2));
    tagsRepository.save( new Tag("COMBUSTIVEL",category2));
    tagsRepository.save( new Tag("ONIBUS",category2));
    tagsRepository.save( new Tag("TAXI",category2));
    tagsRepository.save( new Tag("LAVAGEM",category2));

    tagsRepository.save( new Tag("CORTE DE CABELO",category3));
    tagsRepository.save( new Tag("VESTUÁRIO",category3));
    tagsRepository.save( new Tag("ACADEMIA",category3));
    tagsRepository.save( new Tag("MESADA",category3));


    tagsRepository.save( new Tag("CURSOS",category4));

    tagsRepository.save( new Tag("RESTAURANTES",category7));
    tagsRepository.save( new Tag("BAR",category7));
    tagsRepository.save( new Tag("VIAGEM",category7));
    tagsRepository.save( new Tag("PASSEIO",category7));

    tagsRepository.save( new Tag("TARIFAS DE BANCO",category5));
    tagsRepository.save( new Tag("DOAÇÕES",category5));

    tagsRepository.save( new Tag("SALARIO",category6));
    tagsRepository.save( new Tag("REVENUE",category6));








  }
}
