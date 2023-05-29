package com.eticaret.service;

import com.eticaret.dto.request.SaveCategoryRequestDto;
import com.eticaret.mapper.ICategoryMapper;
import com.eticaret.repository.ICategoryRepository;
import com.eticaret.repository.entity.Category;
import com.eticaret.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService extends ServiceManager<Category,String> {
    private final ICategoryRepository repository;
    public CategoryService(ICategoryRepository repository){
        super(repository);
        this.repository = repository;
    }

    public String save(SaveCategoryRequestDto dto){
        Optional<Category> optionalCategory = repository.findCategoryByCategoryNameIgnoreCase(dto.getCategoryName());
        if(optionalCategory.isEmpty()) {
            repository.save(ICategoryMapper.INSTANCE.fromSaveCategoryRequestDtoToCategory(dto));
            return "Category Saved";
        }
        throw new RuntimeException("Category Already Exist");
    }

    public Category findCategorieById(String categoryId){
        Optional<Category> optionalCategory = repository.findById(categoryId);
        if(optionalCategory.isEmpty())
            throw new RuntimeException("CATEGORY NOT FOUND");
        return optionalCategory.get();
    }
}
