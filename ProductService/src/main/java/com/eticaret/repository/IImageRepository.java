package com.eticaret.repository;

import com.eticaret.repository.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends MongoRepository<Image,String> {
    List<Image> findAllByProductId(String productId);
}
