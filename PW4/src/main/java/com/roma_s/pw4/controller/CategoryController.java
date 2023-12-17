package com.roma_s.pw4.controller;

import com.roma_s.pw4.model.Category;
import com.roma_s.pw4.service.CategoryService;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;

  }

  @GetMapping
  public ResponseEntity<Object> getAllCategory() {
    categoryService.createCategory(new Category(3, "c1"));
    categoryService.createCategory(new Category(4, "category"));
    List<Category> categories = categoryService.getAllCategories();

//    return "products";
    if (categories.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .build();
    }
    return ResponseEntity.status(HttpStatus.OK)
            .body(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getOneCategory(@PathVariable int id) {
    Category category = categoryService.getCategoryById(id).get();
    if (category != null) {
      return ResponseEntity.status(HttpStatus.OK)
              .body(category);
    }
    return ResponseEntity.status(404).build();
  }

  @PostMapping
  public ResponseEntity<Category> create(@RequestBody Category category) {
    if (category.getId() != null) {
      return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
              .build();
    }
    categoryService.createCategory(category);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(category);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateCategory(@PathVariable long id, @RequestBody Category category) {
    if (!Objects.equals(id, category.getId())) {
      throw new IllegalStateException("Id parameter does not match account body value");
    }
    if (category.getId() == null) {
      return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
              .build();
    }
    categoryService.updateCategory(id, category);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> remove(@PathVariable int id) {
    Category category = categoryService.getCategoryById((int) id).get();
    if (category == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .build();
    }
    categoryService.deleteCategory(category.getId());
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build();
  }
}
