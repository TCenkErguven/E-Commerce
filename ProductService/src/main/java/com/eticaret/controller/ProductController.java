package com.eticaret.controller;

import com.eticaret.dto.request.CreateProductRequestDto;
import com.eticaret.dto.request.ProductSaleRequestDto;
import com.eticaret.dto.response.GetProductResponseDto;
import com.eticaret.dto.response.ProductSaleResponseDto;
import com.eticaret.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create-product")
    public ResponseEntity<Boolean> createProduct(CreateProductRequestDto dto){
        return ResponseEntity.ok(productService.createProduct(dto));
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/get-all-products")
    public ResponseEntity<List<GetProductResponseDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @PostMapping("/get-sale-products")
    public ResponseEntity<ProductSaleResponseDto> getSaleProduct(@RequestBody ProductSaleRequestDto dto){
        return ResponseEntity.ok(productService.getSaleProduct(dto));
    }
}
