package com.budget.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.budget.api.TestHelper;
import com.budget.api.entity.Category;
import com.budget.api.exceptions.CustomizedResponseEntityExceptionHandler;
import com.budget.api.exceptions.ResourceNotFoundException;
import com.budget.api.service.CategoriesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class CategoriesControllerTest {

  private MockMvc mvc;

  @Mock
  private CategoriesService categoriesService;

  @InjectMocks
  private CategoriesController categoriesController;

  private JacksonTester<Category> categoryJson;

  @Before
  public void setup() {

    JacksonTester.initFields(this, new ObjectMapper());

    mvc = MockMvcBuilders.standaloneSetup(categoriesController)
        .setControllerAdvice(new CustomizedResponseEntityExceptionHandler())
        .build();

  }

  @Test
  public void mustRetrieveByIdWhenExists() throws Exception {
    // given
    given(categoriesService.findById(1L))
        .willReturn(TestHelper.getSavedCategory());

    // when
    MockHttpServletResponse response = mvc.perform(
        get("/categories/1")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
        categoryJson.write(TestHelper.getSavedCategory()).getJson()
    );
  }

  @Test
  public void mustFailRetrieveById() throws Exception {

    // given
    given(categoriesService.findById(2L))
        .willThrow(new ResourceNotFoundException("Category with id 2 not found"));

    // when
    MockHttpServletResponse response = mvc.perform(
        get("/categories/2")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(response.getContentAsString()).contains("Category with id 2 not found");
  }


  @Test
  public void mustFindAllCategories() throws Exception {
    // given
    given(categoriesService.findAll())
        .willReturn(TestHelper.getCategoryList());

    // when
    MockHttpServletResponse response = mvc.perform(
        get("/categories")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString())
        .contains(categoryJson.write(TestHelper.getSavedCategory()).getJson());

  }

  @Test
  public void mustSaveCategory() throws Exception{
    // given
    given(categoriesService.save(TestHelper.getCategory()))
        .willReturn(TestHelper.getSavedCategory());

    // when
    String json = categoryJson.write(TestHelper.getCategory()).getJson();
    MockHttpServletResponse response = mvc.perform(
        post("/categories")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getHeader("Location")).contains("/categories/1");
  }

  @Test
  public void mustDeleteCategory() throws Exception{

    // when
    String json = categoryJson.write(TestHelper.getCategory()).getJson();
    MockHttpServletResponse response = mvc.perform(
        delete("/categories/1")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isNullOrEmpty();
  }

}
