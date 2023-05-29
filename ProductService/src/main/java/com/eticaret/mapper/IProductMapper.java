package com.eticaret.mapper;

import com.eticaret.dto.request.CreateProductRequestDto;
import com.eticaret.dto.request.ProductSaleRequestDto;
import com.eticaret.dto.response.GetProductResponseDto;
import com.eticaret.dto.response.ProductSaleResponseDto;
import com.eticaret.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    Product fromCreateProductRequestDtoToProduct(final CreateProductRequestDto dto);
    GetProductResponseDto fromProductToGetProductResponseDto(final Product product);
    ProductSaleResponseDto fromProductSaleRequestDtoToProductSaleResponseDto(final ProductSaleRequestDto dto);


}
