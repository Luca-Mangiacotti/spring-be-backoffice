package org.lessons.java.spring_be_backoffice.repository;

import org.lessons.java.spring_be_backoffice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
