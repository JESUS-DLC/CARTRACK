package dev.jesusdlc.cartrack.business.service;

import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarResponseDto findById(long id,String username);
    Car findCar(long id,String username);
    boolean existByIdAndUsername(long id,String username);
    List<CarResponseDto> findAll();
    PageableResponse<CarResponseDto> findAllPageable(Pageable pageable, Long brandId, Long year,String username);
    CarResponseDto save(CarRequestDto carRequestDto, Brand brand, Usuario user);
    CarResponseDto update(CarRequestUpdateDto carRequestDto,Brand brand,Usuario username);
    boolean delete(long carId,String username);
}
