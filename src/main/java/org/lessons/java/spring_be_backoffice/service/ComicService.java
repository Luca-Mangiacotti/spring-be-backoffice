package org.lessons.java.spring_be_backoffice.service;

import java.util.List;
import java.util.Optional;
import org.lessons.java.spring_be_backoffice.model.Comic;
import org.lessons.java.spring_be_backoffice.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicService {

    @Autowired
    private ComicRepository comicRepository;

    // INDEX
    public List<Comic> findAll() {
        return comicRepository.findAll();
    }

    // SEARCH
    public List<Comic> findByTitle(String word) {
        if (word != null) {
            return comicRepository.findByTitleContainingIgnoreCase(word);
        }
        return findAll();

    }

    // SHOW BY ID
    public Comic getById(Integer id) {
        return comicRepository.findById(id).get();
    }

    // FIND BY ID
    public Optional<Comic> findById(Integer id) {
        return comicRepository.findById(id);
    }

    // EXIST BY ID
    public Boolean existById(Integer id) {
        return comicRepository.existsById(id);
    }

    // FIND BY CATEGORY
    public List<Comic> findByCategory(String category) {
        return comicRepository.findByCategoryNameContainingIgnoreCase(category);
    }

    // CREATE
    public Comic create(Comic Comic) {

        return comicRepository.save(Comic);
    }

    // UPDATE
    public Comic update(Comic Comic) {

        return comicRepository.save(Comic);
    }

    // DELETE
    // Delete tramite id
    public void deleteById(Integer id) {

        Comic Comic = getById(id);
        comicRepository.delete(Comic);
    }

}
