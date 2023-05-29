package com.eticaret.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CreateProductRequestDto {
    private String productName;
    @Min(0)
    private double price;
    private String description;
    private String brandId;
    private List<String> categoryIds;
    private List<String> imageUrls;
}
