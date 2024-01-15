package dev.jesusdlc.cartrack.presentation.controller;

import dev.jesusdlc.cartrack.business.facade.ServiceFacade;
import dev.jesusdlc.cartrack.business.mapper.ServiceMapper;
import dev.jesusdlc.cartrack.business.service.impl.AuthServiceImpl;
import dev.jesusdlc.cartrack.domain.dto.request.create.ServiceRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.ServiceRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.ServiceResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Services;
import dev.jesusdlc.cartrack.persistence.repository.ServiceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Services")
@RequiredArgsConstructor
@RequestMapping("/api")
public class ServiceController {

    private final ServiceFacade serviceFacade;

    @Operation(
            summary = "Get a service",
            description = "Get endpoint for service by id"
    )
    @GetMapping("/cars/{carId}/services/{id}")
    ResponseEntity<ServiceResponseDto> findById(@Parameter(description = "car id",example = "2")
                                                @PathVariable("carId") long carId,
                                                @Parameter(description = "service id",example = "4")
                                                @PathVariable("id") long id
    ){
        ServiceResponseDto services = serviceFacade.findById(carId,id);
        return ResponseEntity.status(HttpStatus.OK).body(services);
    }

    @Operation(
            summary = "Get all services with pagination and query params",
            description = "Get endpoint for all services with pagination and query params"
    )
    @GetMapping("/cars/{carId}/services")
    ResponseEntity<PageableResponse<ServiceResponseDto>> findAllPageable(@ParameterObject @PageableDefault(page = 0,size = 10)Pageable pageable,
                                                             @Parameter(description = "car id",example = "2")
                                                             @PathVariable("carId") long carId,
                                                             @Parameter(description = "maximum cost to filter services", example = "500")
                                                             @RequestParam(required = false)BigInteger minCost,
                                                             @Parameter(description = "minimum cost to filter services", example = "300")
                                                             @RequestParam(required = false)BigInteger maxCost,
                                                             @Parameter(description = "date to filter services", example = "2023-01-23")
                                                             @RequestParam(required = false) LocalDate date,
                                                             @Parameter(description = "status to filter services", example = "true")
                                                             @RequestParam(required = false)Boolean status
    ){
        PageableResponse<ServiceResponseDto> services = serviceFacade.findAllPageable(pageable,carId,minCost,maxCost,date,status);
        return ResponseEntity.status(HttpStatus.OK).body(services);
    }

    @Operation(
            summary = "Save a service",
            description = "Post endpoint for save a service"
    )
    @PostMapping("/cars/{carId}/services")
    ResponseEntity<ServiceResponseDto> save(@Valid @RequestBody ServiceRequestDto serviceRequestDto,
                                            @Parameter(description = "car id",example = "2")
                                            @PathVariable("carId") long carId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(serviceFacade.save(carId,serviceRequestDto));
    }

    @Operation(
            summary = "Update service",
            description = "Patch endpoint for update a service"
    )
    @PatchMapping("/cars/{carId}/services")
    ResponseEntity<ServiceResponseDto> update(@Valid @RequestBody ServiceRequestUpdateDto serviceRequestUpdateDto,
                                              @Parameter(description = "car id",example = "2")
                                              @PathVariable("carId") long carId){
        return ResponseEntity.status(HttpStatus.OK).body(serviceFacade.update(carId,serviceRequestUpdateDto));
    }

    @Operation(
            summary = "Delete a service",
            description = "Delete endpoint for delete a service by id"
    )
    @DeleteMapping("/cars/{carId}/services/{id}")
    ResponseEntity<Boolean> delete(@Parameter(description = "car id",example = "2")
                                   @PathVariable("carId") long carId,@PathVariable("id") long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(serviceFacade.delete(carId,id));
    }




}
