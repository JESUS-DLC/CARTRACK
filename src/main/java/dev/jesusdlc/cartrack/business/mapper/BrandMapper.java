package dev.jesusdlc.cartrack.business.mapper;

import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    BrandResponseDto toBrandResponseDto(Brand brand);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    Brand toBrand(BrandRequestDto brandRequestDto);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    Brand toBrandUpdate(BrandRequestUpdateDto brandRequestDto);


    List<BrandResponseDto> toBrandResponseDto(List<Brand> brands);
}
