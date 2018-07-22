package com.budget.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.budget.api.TestHelper;
import com.budget.api.entity.Category;
import com.budget.api.entity.Tag;
import com.budget.api.exceptions.CustomizedResponseEntityExceptionHandler;
import com.budget.api.exceptions.ResourceNotFoundException;
import com.budget.api.repository.CategoriesRepository;
import com.budget.api.service.TagsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class TagsControllerTest {

  private MockMvc mvc;

  @Mock
  private TagsService tagsService;

  @InjectMocks
  private TagsController tagsController;

  private JacksonTester<Tag> tagJacksonTester;


  @Autowired
  CategoriesRepository categoriesRepository;

  Category savedCategory;


  @Before
  public void setup() {

    JacksonTester.initFields(this, new ObjectMapper());

    mvc = MockMvcBuilders.standaloneSetup(tagsController)
        .setControllerAdvice(new CustomizedResponseEntityExceptionHandler())
        .build();
    savedCategory = categoriesRepository.save(TestHelper.getCategory());
  }


  @Test
  public void mustRetrieveByIdWhenExists() throws Exception {
    // given
    given(tagsService.findById(savedCategory.getId(), 1L))
        .willReturn(TestHelper.getSavedTag(savedCategory));

    // when
    MockHttpServletResponse response = mvc.perform(
        get(String.format("/categories/%d/tags/1",savedCategory.getId()))
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
        tagJacksonTester.write(TestHelper.getSavedTag(savedCategory)).getJson()
    );
  }


  @Test
  public void mustFailRetrieveById() throws Exception {

    // given
    given(tagsService.findById(savedCategory.getId(),2L))
        .willThrow(new ResourceNotFoundException("Tag with id 2 not found"));

    // when
    MockHttpServletResponse response = mvc.perform(
        get(String.format("/categories/%d/tags/2",savedCategory.getId()))
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(response.getContentAsString()).contains("Tag with id 2 not found");
  }
}
