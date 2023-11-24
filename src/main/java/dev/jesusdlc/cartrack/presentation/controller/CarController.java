package dev.jesusdlc.cartrack.presentation.controller;

import dev.jesusdlc.cartrack.business.facade.CarFacade;
import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.persistence.repository.CarRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarFacade carFacade;
    private final CarRepository carRepository;

    @GetMapping("/{id}")
    ResponseEntity<CarResponseDto> findById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.findById(id));
    }

    @GetMapping("/test")
    List<Car> findAllTest(){
        return carRepository.findAll();
    }

    @GetMapping("/all")
    ResponseEntity<List<CarResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.findAll());
    }

    @GetMapping()
    ResponseEntity<PageableResponse<CarResponseDto>> findAllPageable(@PageableDefault(page = 0,size = 10)Pageable pageable,
                                                        @RequestParam(required = false) Long brandId,
                                                        @RequestParam(required = false) Long year){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.findAllPageable(pageable,brandId,year));
    }

    @PostMapping
    ResponseEntity<CarResponseDto> save(@Valid @RequestBody CarRequestDto carRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.save(carRequestDto));
    }

    @PatchMapping
    ResponseEntity<CarResponseDto> update(@Valid @RequestBody CarRequestUpdateDto carRequestUpdateDto){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.update(carRequestUpdateDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(carFacade.delete(id));
    }
}
