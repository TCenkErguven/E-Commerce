package com.eticaret.manager;

import com.eticaret.dto.request.ProductSaleRequestDto;
import com.eticaret.dto.response.ProductSaleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "sale-product",
        url = "http://localhost:8060/api/v1/product"
)
public interface IProductManager {
    @PostMapping("/get-sale-products")
    public ResponseEntity<ProductSaleResponseDto> getSaleProduct(@RequestBody ProductSaleRequestDto dto);


}
