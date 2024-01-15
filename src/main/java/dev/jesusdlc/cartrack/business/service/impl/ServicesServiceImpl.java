package dev.jesusdlc.cartrack.business.service.impl;

import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.mapper.ServiceMapper;
import dev.jesusdlc.cartrack.business.service.ServicesService;
import dev.jesusdlc.cartrack.domain.dto.request.create.ServiceRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.ServiceRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.ServiceResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.domain.entity.Services;
import dev.jesusdlc.cartrack.domain.specification.SearchServicesSpecification;
import dev.jesusdlc.cartrack.persistence.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final String RESOURCE = "service";
    @Override
    public ServiceResponseDto findById(long carId, long id, String username) {
        Services service = serviceRepository.findByIdAndCarIdAndUsername(id,carId,username).orElseThrow(()->new NotFoundException(RESOURCE));
        ServiceResponseDto serviceResponseDto = serviceMapper.toServiceResponseDto(service);
        return serviceResponseDto;
    }

    @Override
    public Boolean existsByIdAndCarAndUsername(long carId,long id,String username){
        return serviceRepository.existsByIdAndCarAndUsername(id,carId,username);
    }

    @Override
    public List<ServiceResponseDto> findAll(long carId, String username) {
        List<Services> services = serviceRepository.findByCarIdAndCarUserUsername(carId,username);
        List<ServiceResponseDto> serviceResponseDtos = serviceMapper.toServiceResponseDto(services);
        return serviceResponseDtos;
    }

    @Override
    public PageableResponse<ServiceResponseDto> findAllPageable(Pageable pageable, long carId, BigInteger minCost, BigInteger maxCost, LocalDate date, Boolean status, String username) {
        SearchServicesSpecification specification = new SearchServicesSpecification(carId,minCost,maxCost,date,status,username);
        Page<Services> page = serviceRepository.findAll(specification,pageable);

        List<ServiceResponseDto> services = serviceMapper.toServiceResponseDto(page.getContent());
        PageableResponse<ServiceResponseDto> servicesPageable = new PageableResponse<>();
        servicesPageable.setContent(services);
        servicesPageable.setNumberPage(page.getNumber());
        servicesPageable.setSizePage(page.getSize());
        servicesPageable.setTotalPages(page.getTotalPages());
        servicesPageable.setTotalBrands(page.getTotalElements());
        return servicesPageable;
    }

    @Override
    public ServiceResponseDto save(ServiceRequestDto serviceRequestDto, Car car) {
        Services service = serviceMapper.toService(serviceRequestDto);
        service.setCar(car);
        Services serviceSaved = serviceRepository.save(service);
        ServiceResponseDto serviceResponseDto = serviceMapper.toServiceResponseDto(serviceSaved);
        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto update(ServiceRequestUpdateDto serviceRequestUpdateDto, Car car) {
        Services service = serviceMapper.toServiceByUpdate(serviceRequestUpdateDto);
        service.setCar(car);
        Services serviceSaved = serviceRepository.save(service);
        ServiceResponseDto serviceResponseDto = serviceMapper.toServiceResponseDto(serviceSaved);
        return serviceResponseDto;
    }

    @Override
    public boolean delete(long carId, long id) {
        serviceRepository.deleteById(id);
        return true;
    }
}
