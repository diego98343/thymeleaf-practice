package com.example.PracticeThymeleaf.Data;

import com.example.PracticeThymeleaf.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory,Integer> {
}
