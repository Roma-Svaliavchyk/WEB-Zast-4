package com.roma_s.pw4.repository;

import com.roma_s.pw4.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPaginationRepository extends PagingAndSortingRepository<Product, Integer> {

}
