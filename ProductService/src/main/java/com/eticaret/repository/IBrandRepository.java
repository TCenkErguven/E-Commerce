package com.eticaret.repository;

import com.eticaret.repository.entity.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBrandRepository extends MongoRepository<Brand,String> {
    Optional<Brand> findBrandByBrandNameIgnoreCase(String brandName);
}
