package dev.jesusdlc.cartrack.business.facade;


import dev.jesusdlc.cartrack.domain.dto.request.create.ServiceRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.ServiceRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.ServiceResponseDto;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface ServiceFacade {
    ServiceResponseDto findById(long carId,long id);
    List<ServiceResponseDto> findAll(long carId);
    PageableResponse<ServiceResponseDto> findAllPageable(Pageable pageable, long carId, BigInteger minCost, BigInteger maxCost, LocalDate date, Boolean status  );
    ServiceResponseDto save(long carId,ServiceRequestDto serviceRequestDto);
    ServiceResponseDto update(long carId,ServiceRequestUpdateDto serviceRequestUpdateDto);
    boolean delete(long carId,long id);

}
