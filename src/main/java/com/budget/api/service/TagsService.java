package com.budget.api.service;

import com.budget.api.entity.Tag;
import com.budget.api.exceptions.ResourceNotFoundException;
import com.budget.api.repository.TagsRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsService {

  private static final String TAG_NOT_FOUND_MESSAGE = "Tag with id %d not found";

  private static final Logger LOGGER = LoggerFactory.getLogger(TagsService.class);

  private TagsRepository tagsRepository;

  @Autowired
  public TagsService(TagsRepository tagsRepository) {
    this.tagsRepository = tagsRepository;
  }

  public List<Tag> findAll(Long categoryId) {
    return tagsRepository.findAllByCategoryId(categoryId);
  }

  public Tag save(Tag tag) {
    return tagsRepository.save(tag);
  }

  public void delete(Long id) {
    tagsRepository.deleteById(id);
  }

  public Tag findById(Long categoryId, Long id) {
    Optional<Tag> optionalTag = tagsRepository.findByCategoryIdAndId(categoryId, id);
    return optionalTag
        .orElseThrow(() -> new ResourceNotFoundException(String.format(TAG_NOT_FOUND_MESSAGE, id)));
  }

}
