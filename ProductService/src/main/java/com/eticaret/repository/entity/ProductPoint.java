package com.eticaret.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@SuperBuilder
@Data
@NoArgsConstructor
@Document
public class ProductPoint extends Base{
    @Id
    private String id;
    private String userId;
    @Min(0)
    @Max(5)
    private double pointValue;
    private String productId;
}
