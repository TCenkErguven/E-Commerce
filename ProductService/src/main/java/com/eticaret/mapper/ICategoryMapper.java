package com.eticaret.mapper;

import com.eticaret.dto.request.SaveCategoryRequestDto;
import com.eticaret.repository.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {
    ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);

    Category fromSaveCategoryRequestDtoToCategory(final SaveCategoryRequestDto dto);
}
