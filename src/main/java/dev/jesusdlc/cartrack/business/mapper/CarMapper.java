package dev.jesusdlc.cartrack.business.mapper;

import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Car;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {


    @Mapping(target = "id",source = "id")
    @Mapping(target = "model",source = "model")
    @Mapping(target = "year",source = "year")
    @Mapping(target = "brandId",source = "brand.id")
    @Mapping(target = "brand",source = "brand.name")
    CarResponseDto toCarResponseDto(Car car);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "model",source = "model")
    @Mapping(target = "year",source = "year")
    @Mapping(target = "brand",ignore = true)
    Car toCar(CarRequestDto carRequestDto);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "model",source = "model")
    @Mapping(target = "year",source = "year")
    @Mapping(target = "brand",ignore = true)
    Car toCarByUpdate(CarRequestUpdateDto carRequestUpdateDto);

    List<CarResponseDto> toCarResponseDto(List<Car> cars);
}
