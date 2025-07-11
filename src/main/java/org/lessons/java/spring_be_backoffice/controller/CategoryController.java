package org.lessons.java.spring_be_backoffice.controller;

import java.util.List;

import org.lessons.java.spring_be_backoffice.model.Category;
import org.lessons.java.spring_be_backoffice.model.Comic;
import org.lessons.java.spring_be_backoffice.service.CategoryService;
import org.lessons.java.spring_be_backoffice.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // INDEX DELLE CATEGORIES
    @GetMapping
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categories", categories);

        return "categories/index";
    }

    // INDEX in base alla categoria
    @Autowired
    private ComicService comicService;

    @GetMapping("/{id}/comics")
    public String comicsByCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return "redirect:/categories";
        }
        List<Comic> comics = comicService.findByCategory(category.getName());

        model.addAttribute("category", category);
        model.addAttribute("comics", comics);

        return "categories/comics-by-category"; // crea questa pagina Thymeleaf
    }

    // DETTAGLIO DI UNA Category (SHOW)

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        // con questo comando è come fare una query SELECT * from categories where "id"
        // =id
        Category category = categoryService.getById(id);

        if (category == null) {
            return "rederict:/categories";
        }
        model.addAttribute("category", category);

        return "categories/detail";
    }

    // AGGIUNTA DI UNA NUOVA Category (CREATE)

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("edit", false);
        return "categories/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category CategoryForm, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "categories/create-edit";
        }
        categoryService.create(CategoryForm);
        return "redirect:/categories";

    }

    // MODIFICA DI UN Category (UPDATE)

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("edit", true);
        return "categories/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("category") Category CategoryForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/create-edit";
        }
        categoryService.update(CategoryForm);
        return "redirect:/categories";

    }

    // ELIMINAZIONE DI UN Category (DELETE)

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
