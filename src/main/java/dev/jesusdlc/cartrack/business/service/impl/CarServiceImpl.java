package dev.jesusdlc.cartrack.business.service.impl;

import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.mapper.CarMapper;
import dev.jesusdlc.cartrack.business.service.CarService;
import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import dev.jesusdlc.cartrack.domain.specification.SearchCarSpecification;
import dev.jesusdlc.cartrack.persistence.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final String RESOURCE = "car";

    @Override
    public CarResponseDto findById(long id,String username) {
        Car car = carRepository.findByIdAndUserUsername(id,username).orElseThrow(()->new NotFoundException(RESOURCE));
        CarResponseDto carResponseDto = carMapper.toCarResponseDto(car);
        return carResponseDto;
    }

    @Override
    public Car findCar(long id,String username){
        Car car = carRepository.findByIdAndUserUsername(id,username).orElseThrow(()->new NotFoundException(RESOURCE));
        return car;
    }

    @Override
    public boolean existByIdAndUsername(long id,String username) {
        return carRepository.existsByIdAndUserUsername(id,username);
    }

    @Override
    public List<CarResponseDto> findAll() {
        List<Car> cars = carRepository.findAll();
        List<CarResponseDto> carsResponse = carMapper.toCarResponseDto(cars);
        return carsResponse;
    }

    @Override
    public PageableResponse<CarResponseDto> findAllPageable(Pageable pageable, Long brandId, Long year,String username) {
        SearchCarSpecification specification = new SearchCarSpecification(brandId,year,username);

        Page<Car> page = carRepository.findAll(specification,pageable);

        List<CarResponseDto> cars = carMapper.toCarResponseDto(page.getContent());
        PageableResponse<CarResponseDto> carPageable = new PageableResponse<>();
        carPageable.setContent(cars);
        carPageable.setNumberPage(page.getNumber());
        carPageable.setSizePage(page.getSize());
        carPageable.setTotalPages(page.getTotalPages());
        carPageable.setTotalElements(page.getTotalElements());

        return carPageable;
    }

    @Override
    public CarResponseDto save(CarRequestDto carRequestDto, Brand brand,Usuario user) {
        Car car = carMapper.toCar(carRequestDto);
        car.setBrand(brand);
        car.setUser(user);
        Car carSaved = carRepository.save(car);
        CarResponseDto carResponse = carMapper.toCarResponseDto(carSaved);
        return carResponse;
    }

    @Override
    public CarResponseDto update(CarRequestUpdateDto carRequestDto,Brand brand,Usuario user) {
        Car car = carMapper.toCarByUpdate(carRequestDto);
        car.setBrand(brand);
        car.setUser(user);
        Car carSaved = carRepository.save(car);
        CarResponseDto carResponse = carMapper.toCarResponseDto(carSaved);
        return carResponse;
    }

    @Override
    public boolean delete(long carId,String username) {
        if(!carRepository.existsByIdAndUserUsername(carId,username)) throw new NotFoundException(RESOURCE);
        carRepository.deleteById(carId);
        return true;
    }
}
