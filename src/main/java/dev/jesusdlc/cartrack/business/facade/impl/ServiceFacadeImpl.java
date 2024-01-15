package dev.jesusdlc.cartrack.business.facade.impl;

import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.facade.ServiceFacade;
import dev.jesusdlc.cartrack.business.service.AuthService;
import dev.jesusdlc.cartrack.business.service.CarService;
import dev.jesusdlc.cartrack.business.service.ServicesService;
import dev.jesusdlc.cartrack.business.service.UserService;
import dev.jesusdlc.cartrack.domain.dto.request.create.ServiceRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.ServiceRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.ServiceResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceFacadeImpl implements ServiceFacade {

    private final ServicesService service;
    private final CarService carService;
    private final AuthService authService;
    private final UserService userService;
    @Override
    public ServiceResponseDto findById(long carId, long id) {
        String username = authService.getAuthUsername();
        return service.findById(carId,id,username);
    }

    @Override
    public List<ServiceResponseDto> findAll(long carId) {
        String username = authService.getAuthUsername();
        return service.findAll(carId,username);
    }

    @Override
    public PageableResponse<ServiceResponseDto> findAllPageable(Pageable pageable, long carId, BigInteger minCost, BigInteger maxCost, LocalDate date, Boolean status) {
        String username = authService.getAuthUsername();
        return service.findAllPageable(pageable,carId,minCost,maxCost,date,status,username);
    }

    @Override
    public ServiceResponseDto save(long carId, ServiceRequestDto serviceRequestDto) {
        String username = authService.getAuthUsername();
        Car car = carService.findCar(carId,username);
        return service.save(serviceRequestDto,car);
    }

    @Override
    public ServiceResponseDto update(long carId, ServiceRequestUpdateDto serviceRequestUpdateDto) {
        String username = authService.getAuthUsername();
        Car car = carService.findCar(carId,username);
        if(!service.existsByIdAndCarAndUsername(carId, serviceRequestUpdateDto.getId(), username)) throw new NotFoundException("SERVICE");
        return service.update(serviceRequestUpdateDto,car);
    }

    @Override
    public boolean delete(long carId, long id) {
        String username = authService.getAuthUsername();
        if(!service.existsByIdAndCarAndUsername(carId, id, username)) throw new NotFoundException("SERVICE");
        return service.delete(carId,id);
    }
}
