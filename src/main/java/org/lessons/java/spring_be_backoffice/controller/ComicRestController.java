package org.lessons.java.spring_be_backoffice.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_be_backoffice.model.Comic;
import org.lessons.java.spring_be_backoffice.service.CategoryService;
import org.lessons.java.spring_be_backoffice.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comics")
public class ComicRestController {

    @Autowired
    private ComicService comicService;

    // Index
    @GetMapping
    public List<Comic> index(@RequestParam(name = "word", required = false) String word,
            @RequestParam(name = "category", required = false) String category) {
        if (word != null && category != null) {
            return comicService.findByTitleAndCategory(word, category);
        } else if (category != null) {
            return comicService.findByCategory(category);
        } else if (word != null) {
            return comicService.findByTitle(word);
        }
        return comicService.findAll();
    }

    // Show by id
    @GetMapping("/{id}")
    public ResponseEntity<Comic> show(@PathVariable Integer id) {
        Optional<Comic> comicAttempt = comicService.findById(id);
        if (comicAttempt.isEmpty()) {
            return new ResponseEntity<Comic>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Comic>(comicAttempt.get(), HttpStatus.OK);
    }

    // Create
    @PostMapping
    public ResponseEntity<Comic> store(@RequestBody Comic comic) {
        return new ResponseEntity<Comic>(comicService.create(comic), HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Comic> update(@RequestBody Comic comic, @PathVariable Integer id) {
        if (comicService.findById(id).isEmpty()) {
            return new ResponseEntity<Comic>(HttpStatus.NOT_FOUND);

        }
        comic.setId(id);
        return new ResponseEntity<Comic>(comicService.update(comic), HttpStatus.OK);
    }

    // Delete By Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Comic> deleteById(@PathVariable Integer id) {

        if (comicService.findById(id).isEmpty()) {
            return new ResponseEntity<Comic>(HttpStatus.NOT_FOUND);

        }
        comicService.deleteById(id);
        return new ResponseEntity<Comic>(HttpStatus.OK);
    }
}
