package com.eticaret.mapper;

import com.eticaret.dto.request.SaveBrandRequestDto;
import com.eticaret.repository.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBrandMapper {
    IBrandMapper INSTANCE = Mappers.getMapper(IBrandMapper.class);

    Brand fromSaveBrandRequestDtoToBrand(final SaveBrandRequestDto dto);

}
