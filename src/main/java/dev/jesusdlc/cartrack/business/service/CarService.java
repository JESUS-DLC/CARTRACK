package dev.jesusdlc.cartrack.business.service;

import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarResponseDto findById(long id);

    boolean existById(long id);
    List<CarResponseDto> findAll();
    PageableResponse<CarResponseDto> findAllPageable(Pageable pageable, Long brandId, Long year);
    CarResponseDto save(CarRequestDto carRequestDto, Brand brand);
    CarResponseDto update(CarRequestUpdateDto carRequestDto,Brand brand);
    boolean delete(long carId);
}
