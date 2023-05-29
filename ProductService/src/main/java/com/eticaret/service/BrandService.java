package com.eticaret.service;

import com.eticaret.dto.request.SaveBrandRequestDto;
import com.eticaret.mapper.IBrandMapper;
import com.eticaret.repository.IBrandRepository;
import com.eticaret.repository.entity.Brand;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService extends ServiceManager<Brand,String> {
    private final IBrandRepository repository;
    public BrandService(IBrandRepository repository){
        super(repository);
        this.repository = repository;
    }

    public String save(SaveBrandRequestDto dto){
        Optional<Brand> optionalBrand = repository.findBrandByBrandNameIgnoreCase(dto.getBrandName());
        if(optionalBrand.isEmpty()) {
            save(IBrandMapper.INSTANCE.fromSaveBrandRequestDtoToBrand(dto));
            return "Brand Saved";
        }
        throw new RuntimeException("Brand Already Exist");
    }

    public Optional<Brand> findById(String brandId){
        Optional<Brand> optionalBrand = repository.findById(brandId);
        if(optionalBrand.isEmpty()){
            throw new RuntimeException("BRAND NOT FOUND");
        }
        return optionalBrand;
    }
}
