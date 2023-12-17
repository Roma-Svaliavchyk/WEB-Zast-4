package com.roma_s.pw4.service;

import com.roma_s.pw4.model.Category;
import com.roma_s.pw4.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAllCategories() {
    Iterable<Category> itCategories = categoryRepository.findAll();
    List<Category> categories = new ArrayList<>();
    itCategories.forEach(categories::add);

    return categories;
  }

  public Optional<Category> getCategoryById(int id) {
    return categoryRepository.findById(id);
  }

  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Optional<Category> updateCategory(long id, Category updatedCategory) {
    if (categoryRepository.existsById((int) id)) {
      updatedCategory.setId(id);
      return Optional.of(categoryRepository.save(updatedCategory));
    } else {
      return Optional.empty();
    }
  }

  public void deleteCategory(long id) {
    categoryRepository.deleteById((int)id);
  }
}
