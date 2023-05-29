package com.eticaret.mapper;

import com.eticaret.dto.request.CreateProductRequestDto;
import com.eticaret.dto.request.SaveImageRequestDto;
import com.eticaret.repository.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IImageMapper {
    IImageMapper INSTANCE = Mappers.getMapper(IImageMapper.class);



}
