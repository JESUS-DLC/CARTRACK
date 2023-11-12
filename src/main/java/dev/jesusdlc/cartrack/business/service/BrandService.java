package dev.jesusdlc.cartrack.business.service;

import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandPageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {

    List<BrandResponseDto> findAll();
    BrandPageableResponse findAllPageable(Pageable pageable);
    BrandResponseDto findById(long id);
    BrandResponseDto save(BrandRequestDto brandRequestDto);
    BrandResponseDto update(BrandRequestUpdateDto brandRequestUpdateDto);
    boolean delete(long id);

}
