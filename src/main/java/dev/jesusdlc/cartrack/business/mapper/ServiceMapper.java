package dev.jesusdlc.cartrack.business.mapper;

import dev.jesusdlc.cartrack.domain.dto.request.create.ServiceRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.ServiceRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.ServiceResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Services;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "id",source = "id")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "description",source = "description")
    @Mapping(target = "cost",source = "cost")
    @Mapping(target = "date",source = "date")
    @Mapping(target = "mileage",source = "mileage")
    @Mapping(target = "place",source = "place")
    @Mapping(target = "status",source = "status")
    @Mapping(target = "car",source = "car.id")
    ServiceResponseDto toServiceResponseDto(Services services);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "car",ignore = true)
    Services toService(ServiceRequestDto serviceRequestDto);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "description",source = "description")
    @Mapping(target = "cost",source = "cost")
    @Mapping(target = "date",source = "date")
    @Mapping(target = "mileage",source = "mileage")
    @Mapping(target = "place",source = "place")
    @Mapping(target = "status",source = "status")
    @Mapping(target = "car",ignore = true)
    Services toServiceByUpdate(ServiceRequestUpdateDto serviceRequestUpdateDto);

    List<ServiceResponseDto> toServiceResponseDto(List<Services> services);
}
