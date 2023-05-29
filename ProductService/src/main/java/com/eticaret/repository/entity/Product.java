package com.eticaret.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@SuperBuilder
@Data
@NoArgsConstructor
@Document
public class Product extends Base{
    @Id
    private String id;
    private String productName;
    @Min(0)
    private double price;
    private String description;
    private List<String> categoryIds;
    private String brandId;
}
