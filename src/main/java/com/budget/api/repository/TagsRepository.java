package com.budget.api.repository;

import com.budget.api.entity.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag, Long> {

  List<Tag> findAllByCategoryId(Long categoryId);
  Optional<Tag> findByCategoryIdAndId(Long categoryId,Long Id);
}
