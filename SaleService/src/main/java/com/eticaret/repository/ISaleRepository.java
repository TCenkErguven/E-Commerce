package com.eticaret.repository;

import com.eticaret.repository.entity.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends MongoRepository<Sale,String> {
}
