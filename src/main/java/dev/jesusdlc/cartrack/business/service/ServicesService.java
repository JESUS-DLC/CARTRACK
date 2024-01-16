package dev.jesusdlc.cartrack.business.service;


import dev.jesusdlc.cartrack.domain.dto.request.create.ServiceRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.ServiceRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.ServiceResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.TotalCostServicesDto;
import dev.jesusdlc.cartrack.domain.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface ServicesService {

    ServiceResponseDto findById(long carId,long id,String username);
    List<ServiceResponseDto> findAll(long carId,String username);
    Boolean existsByIdAndCarAndUsername(long carId,long id,String username);
    TotalCostServicesDto getTotalCostServices(String username);
    PageableResponse<ServiceResponseDto> findAllServices(Pageable pageableResponse,BigInteger minCost, BigInteger maxCost, LocalDate date, Boolean status, String username);
    PageableResponse<ServiceResponseDto> findAllPageable(Pageable pageable, long carId, BigInteger minCost, BigInteger maxCost, LocalDate date, Boolean status, String username);
    ServiceResponseDto save(ServiceRequestDto serviceRequestDto, Car car);
    ServiceResponseDto update(ServiceRequestUpdateDto serviceRequestUpdateDto, Car car);
    boolean delete(long carId,long id);
}
