package dev.jesusdlc.cartrack.business.facade;

import dev.jesusdlc.cartrack.domain.dto.request.create.CarRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.CarRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.CarResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.specification.SearchCarSpecification;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarFacade {

    CarResponseDto findById(long id);
    List<CarResponseDto> findAll();
    PageableResponse<CarResponseDto> findAllPageable(Pageable pageable, Long brandId,Long year);
    CarResponseDto save(CarRequestDto carRequestDto);
    CarResponseDto update(CarRequestUpdateDto carRequestDto);
    boolean delete(long carId);
}
