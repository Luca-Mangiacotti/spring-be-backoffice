package org.lessons.java.spring_be_backoffice.controller;

import java.util.List;

import org.lessons.java.spring_be_backoffice.model.Comic;
import org.lessons.java.spring_be_backoffice.service.CategoryService;
import org.lessons.java.spring_be_backoffice.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/comics")
public class ComicController {
    @Autowired
    private ComicService comicService;
    @Autowired
    private CategoryService categoryService;

    // INDEX DEI COMICS
    @GetMapping
    public String index(@RequestParam(name = "word", required = false) String word, Model model,
            Authentication authentication) {
        List<Comic> comics;
        if (word != null) {
            comics = comicService.findByTitle(word);
        } else {
            comics = comicService.findAll();
        }

        model.addAttribute("comics", comics);
        model.addAttribute("word", word);
        model.addAttribute("username", authentication.getName());

        return "comics/index";
    }

    // DETTAGLIO DI UN COMIC (SHOW)

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        // con questo comando è come fare una query SELECT * from pizzas where "id" =id
        Comic comic = comicService.getById(id);

        if (comic == null) {
            return "rederict:/comics";
        }
        model.addAttribute("comic", comic);

        return "comics/detail";
    }

    // AGGIUNTA DI UN NUOVO COMIC (CREATE)

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("comic", new Comic());
        model.addAttribute("categories", categoryService.findAll());
        return "comics/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("comic") Comic comicForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "comics/create-edit";
        }
        comicService.create(comicForm);
        return "redirect:/comics";

    }

    // MODIFICA DI UN COMIC (UPDATE)

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("comic", comicService.getById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("edit", true);
        return "comics/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("comic") Comic comicForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "comics/create-edit";
        }
        comicService.update(comicForm);
        return "redirect:/comics/{id}";

    }

    // ELIMINAZIONE DI UN COMIC (DELETE)

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        comicService.deleteById(id);
        return "redirect:/comics";
    }

}
