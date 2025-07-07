package org.lessons.java.spring_be_backoffice.repository;

import java.util.List;

import org.lessons.java.spring_be_backoffice.model.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Integer> {

    public List<Comic> findByTitleContainingIgnoreCase(String title);

    public List<Comic> findByCategoryNameContainingIgnoreCase(String name);

}
