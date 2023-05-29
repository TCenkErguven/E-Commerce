package com.eticaret.controller;

import com.eticaret.dto.request.SaveCategoryRequestDto;
import com.eticaret.repository.entity.Category;
import com.eticaret.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SaveCategoryRequestDto dto){
        return ResponseEntity.ok(categoryService.save(dto));
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

}
