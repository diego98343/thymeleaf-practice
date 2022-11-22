package com.example.PracticeThymeleaf.Data;

import com.example.PracticeThymeleaf.models.EventTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  EventTagRepository extends CrudRepository<EventTag,Integer> {

}
