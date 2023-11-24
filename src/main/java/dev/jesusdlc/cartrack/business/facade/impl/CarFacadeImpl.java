package dev.jesusdlc.cartrack.business.facade.impl;

import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.facade.CarFacade;
import dev.jesusdlc.cartrack.business.service.BrandService;
import dev.jesusdlc.cartrack.business.service.CarService;
import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarFacadeImpl implements CarFacade {

    private final CarService carService;
    private final BrandService brandService;

    @Override
    public CarResponseDto findById(long id) {
        return carService.findById(id);
    }

    @Override
    public List<CarResponseDto> findAll() {
        return carService.findAll();
    }

    @Override
    public PageableResponse<CarResponseDto> findAllPageable(Pageable pageable, Long brandId,Long year) {
        return carService.findAllPageable(pageable,brandId,year);
    }

    @Override
    public CarResponseDto save(CarRequestDto carRequestDto) {
        Brand brand = brandService.findBrand(carRequestDto.getBrand());
        CarResponseDto car = carService.save(carRequestDto,brand);
        return car;
    }

    @Override
    public CarResponseDto update(CarRequestUpdateDto carRequestDto) {
        Brand brand = brandService.findBrand(carRequestDto.getBrand());
        if(!carService.existById(carRequestDto.getId())) throw new NotFoundException(carRequestDto.getId());
        CarResponseDto car = carService.update(carRequestDto,brand);
        return car;
    }

    @Override
    public boolean delete(long carId) {
        return carService.delete(carId);
    }
}
