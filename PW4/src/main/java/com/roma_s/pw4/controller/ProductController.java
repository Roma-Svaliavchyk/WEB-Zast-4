package com.roma_s.pw4.controller;


import com.roma_s.pw4.model.Category;
import com.roma_s.pw4.model.Product;
import com.roma_s.pw4.service.CategoryService;
import com.roma_s.pw4.service.ProductService;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  private final CategoryService categoryService;

  public ProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public String getAll(Model model) {
    // Створення продуктів
    categoryService.createCategory(new Category(1, "Дорогоцінний камінь"));
    categoryService.createCategory(new Category(2, "Планета"));
    categoryService.createCategory(new Category(3, "Книги"));

    productService.createProduct(new Product(1L, "алмаз", 26000, "Опис блістяшка", new Category(1, "Дорогоцінний камінь")));
    productService.createProduct(new Product(2L, "Юпітер", 729050, "Опис велика планета", new Category(2, "Планета")));
    productService.createProduct(new Product(3L, "Відьмак", 1400, "Опис хороша книжки", new Category(3, "Книги")));
    productService.createProduct(new Product(4L, "Марс", 502400, "Опис червона планета", new Category(2, "Планета")));

    List<Product> products = productService.getAllProducts();


    model.addAttribute("products", products);
    return "products.html";

  }

  @GetMapping("/{id}")
  public String getOne(@PathVariable int id, Model model) {
    Product product = productService.getProductById(id).get();

    model.addAttribute("product", product);

    return "products.html";

  }

  @GetMapping("/{categoryName}")
  public String getByCategory(@PathVariable String categoryName, Model model){
    List<Product> products = productService.getSortedProduct(categoryName);

    model.addAttribute("products", products);

    return "products";
  }

  @GetMapping("top-10-products")
  public String getTop10Products(Model model){
    List<Product> products = productService.getFirst10Products();

    model.addAttribute("products", products);

    return "products.html";
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody Product product) {

    productService.createProduct(product);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable long id, @RequestBody Product product) {
    if (!Objects.equals(id, product.getId())) {
      throw new IllegalStateException("Id parameter does not match account body value");
    }
    productService.updateProduct(id, product);

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remove(@PathVariable long id) {
    Product product = productService.getProductById((int) id).get();
    productService.deleteProduct(product.getId());

  }

}
