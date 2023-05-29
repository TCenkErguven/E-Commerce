package com.eticaret.repository;


import com.eticaret.repository.entity.ProductPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductPointRepository extends MongoRepository<ProductPoint,String> {
}
