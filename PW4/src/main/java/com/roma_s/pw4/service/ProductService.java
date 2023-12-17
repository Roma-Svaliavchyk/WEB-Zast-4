package com.roma_s.pw4.service;

import com.roma_s.pw4.model.Product;
import com.roma_s.pw4.repository.ProductPaginationRepository;
import com.roma_s.pw4.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductPaginationRepository productPaginationRepository;

  public ProductService(ProductRepository productRepository, ProductPaginationRepository productPaginationRepository) {
    this.productRepository = productRepository;
    this.productPaginationRepository = productPaginationRepository;
  }

  public List<Product> getAllProducts() {
    Iterable<Product> itProducts = productRepository.findAll();
    List<Product> products = new ArrayList<>();
    itProducts.forEach(products::add);

    return products;
  }

  public Optional<Product> getProductById(int id) {
    return productRepository.findById(id);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> updateProduct(long id, Product updatedProduct) {
    if (productRepository.existsById((int) id)) {
      updatedProduct.setId(id);
      return Optional.of(productRepository.save(updatedProduct));
    } else {
      return Optional.empty();
    }
  }

  public void deleteProduct(long id) {
    productRepository.deleteById((int) id);
  }

  public List<Product> getSortedProduct(String categoryName){
    Iterable<Product> itProducts = productRepository.findAll();
    List<Product> products = new ArrayList<>();
    itProducts.forEach(products::add);
    products = products.stream().filter(product -> {
      return product.getCategory().getName().equals(categoryName);
    }).toList();

    return products;
  }

  public List<Product> getFirst10Products (){
    Pageable productPage = PageRequest.of(0,10);
    Iterable<Product> itProducts = productPaginationRepository.findAll(productPage);
    List<Product> products = new ArrayList<>();
    itProducts.forEach(products::add);

    return products;
  }
}
