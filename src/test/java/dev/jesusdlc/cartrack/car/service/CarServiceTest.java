package dev.jesusdlc.cartrack.car.service;

import dev.jesusdlc.cartrack.business.mapper.CarMapper;
import dev.jesusdlc.cartrack.business.mapper.CarMapperImpl;
import dev.jesusdlc.cartrack.business.service.CarService;
import dev.jesusdlc.cartrack.business.service.impl.CarServiceImpl;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.persistence.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @Spy
    private CarMapper carMapper = new CarMapperImpl();
    @InjectMocks
    private CarServiceImpl carService;

    private Car car;
    private Brand brand;

    @BeforeEach
    void setUp(){
        brand = new Brand(1L,"Nissan");
        car = Car.builder().id(1L).model("Skyline").year(2004).brand(brand).build();
    }

    @Test
    void findById(){
        long carId = 1L;
        given(carRepository.findById(carId)).willReturn(Optional.of(car));
        CarResponseDto carResponseDto = carService.findById(carId);
        assertEquals(carId,carResponseDto.getId());
    }


}
