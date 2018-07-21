package com.budget.api.controllers;

import com.budget.api.entity.Tag;
import com.budget.api.service.TagsService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/categories/{categoryId}/tags")
public class TagsController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TagsController.class);

  private TagsService tagsService;

  @Autowired
  public TagsController(TagsService tagsService) {
    this.tagsService = tagsService;
  }


  @GetMapping
  public List<Tag> findAll(@PathVariable("categoryId") Long categoryId) {
    return tagsService.findAll(categoryId);
  }

  @GetMapping("/{id}")
  public Tag findById(@PathVariable("categoryId") Long categoryId, @PathVariable("id") Long id) {
    return tagsService.findById(categoryId, id);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Tag tag){
    Tag savedTag = tagsService.save(tag);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedTag.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id){
    tagsService.delete(id);
  }

}
