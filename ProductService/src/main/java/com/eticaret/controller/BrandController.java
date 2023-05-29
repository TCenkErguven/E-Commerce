package com.eticaret.controller;

import com.eticaret.dto.request.SaveBrandRequestDto;
import com.eticaret.repository.entity.Brand;
import com.eticaret.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid SaveBrandRequestDto dto){
        return ResponseEntity.ok(brandService.save(dto));
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<Brand>> findAll(){
        return ResponseEntity.ok(brandService.findAll());
    }
}
