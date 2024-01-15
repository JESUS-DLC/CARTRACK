package dev.jesusdlc.cartrack.presentation.controller;

import dev.jesusdlc.cartrack.business.facade.CarFacade;
import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.persistence.repository.CarRepository;
import io.swagger.v3.oas.annotations.Hidden;
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

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Cars")
@RequiredArgsConstructor
public class CarController {

    private final CarFacade carFacade;

    @Operation(
            summary = "Get a car",
            description = "Get endpoint for car by id"
    )
    @GetMapping("/{id}")
    ResponseEntity<CarResponseDto> findById(@Parameter(description = "service id",example = "2") @PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.findById(id));
    }

    @Hidden
    @GetMapping("/all")
    ResponseEntity<List<CarResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.findAll());
    }

    @Operation(
            summary = "Get all cars with pagination and query params",
            description = "Get endpoint for all cars with pagination and query params"
    )
    @GetMapping()
    ResponseEntity<PageableResponse<CarResponseDto>> findAllPageable(@ParameterObject @PageableDefault(page = 0,size = 10)Pageable pageable,
                                                                     @Parameter(description = "brand id to filter cars",example = "1")
                                                                     @RequestParam(required = false) Long brandId,
                                                                     @Parameter(description = "Manufacturing year to filter cars", example = "2023")
                                                                     @RequestParam(required = false) Long year){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.findAllPageable(pageable,brandId,year));
    }

    @Operation(
            summary = "Save a car",
            description = "Post endpoint for save a car"
    )
    @PostMapping
    ResponseEntity<CarResponseDto> save(@Valid @RequestBody CarRequestDto carRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.save(carRequestDto));
    }

    @Operation(
            summary = "Update a car",
            description = "Patch endpoint for update a car"
    )
    @PatchMapping
    ResponseEntity<CarResponseDto> update(@Valid @RequestBody CarRequestUpdateDto carRequestUpdateDto){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.update(carRequestUpdateDto));
    }

    @Operation(
            summary = "Delete a car",
            description = "Delete endpoint for delete a car by id"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteById(@Parameter(description = "car id",example = "2") @PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.delete(id));
    }
}
