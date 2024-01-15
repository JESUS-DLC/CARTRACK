package dev.jesusdlc.cartrack.business.facade.impl;

import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.facade.CarFacade;
import dev.jesusdlc.cartrack.business.service.AuthService;
import dev.jesusdlc.cartrack.business.service.BrandService;
import dev.jesusdlc.cartrack.business.service.CarService;
import dev.jesusdlc.cartrack.business.service.UserService;
import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarFacadeImpl implements CarFacade {

    private final CarService carService;
    private final BrandService brandService;
    private final AuthService authService;
    private final UserService userService;
    private final String RESOURCE = "car";

    @Override
    public CarResponseDto findById(long id) {
        String username = authService.getAuthUsername();
        return carService.findById(id,username);
    }

    @Override
    public List<CarResponseDto> findAll() {
        return carService.findAll();
    }

    @Override
    public PageableResponse<CarResponseDto> findAllPageable(Pageable pageable, Long brandId,Long year) {
        String username = authService.getAuthUsername();
        return carService.findAllPageable(pageable,brandId,year,username);
    }

    @Override
    public CarResponseDto save(CarRequestDto carRequestDto) {
        Brand brand = brandService.findBrand(carRequestDto.getBrand());
        String username = authService.getAuthUsername();
        Usuario user = userService.findByUsername(username).orElseThrow(()->new NotFoundException("user"));
        CarResponseDto car = carService.save(carRequestDto,brand,user);
        return car;
    }

    @Override
    public CarResponseDto update(CarRequestUpdateDto carRequestDto) {
        Brand brand = brandService.findBrand(carRequestDto.getBrand());
        String username = authService.getAuthUsername();
        Usuario user = userService.findByUsername(username).orElseThrow(()->new NotFoundException("user"));
        if(!carService.existByIdAndUsername(carRequestDto.getId(),username)) throw new NotFoundException(RESOURCE);
        CarResponseDto car = carService.update(carRequestDto,brand,user);
        return car;
    }

    @Override
    public boolean delete(long carId) {
        String username = authService.getAuthUsername();
        return carService.delete(carId,username);
    }
}
