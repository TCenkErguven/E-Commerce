package com.eticaret.service;

import com.eticaret.repository.IProductPointRepository;
import com.eticaret.repository.entity.ProductPoint;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ProductPointService extends ServiceManager<ProductPoint,String> {
    private final IProductPointRepository repository;
    public ProductPointService(IProductPointRepository repository){
        super(repository);
        this.repository = repository;
    }
}
