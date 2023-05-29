package com.eticaret.dto.response;

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
public class GetProductResponseDto {
    private String id;
    private String productName;
    private double price;
    private String description;
    private String brandName;
    private List<String> categoryNames;
    private List<String> imageUrls;
}
