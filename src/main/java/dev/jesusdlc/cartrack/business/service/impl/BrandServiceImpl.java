package dev.jesusdlc.cartrack.business.service.impl;

import dev.jesusdlc.cartrack.business.exception.ExistsException;
import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.mapper.BrandMapper;
import dev.jesusdlc.cartrack.business.service.BrandService;
import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandPageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.persistence.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    @Override
    public List<BrandResponseDto> findAll() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandResponseDto> brandsResponse = brandMapper.toBrandResponseDto(brands);
        return brandsResponse;
    }

    @Override
    public BrandPageableResponse findAllPageable(Pageable pageable){
        Page<Brand>  brandsPageable = brandRepository.findAll(pageable);
        List<Brand> brands = brandsPageable.getContent();
        List<BrandResponseDto> brandResponse = brandMapper.toBrandResponseDto(brands);

        BrandPageableResponse brandPageableResponse = new BrandPageableResponse();
        brandPageableResponse.setBrands(brandResponse);
        brandPageableResponse.setTotalBrands(brandsPageable.getTotalElements());
        brandPageableResponse.setNumberPage(brandsPageable.getNumber());
        brandPageableResponse.setSizePage(brandsPageable.getSize());
        brandPageableResponse.setTotalPages(brandsPageable.getTotalPages());

        return brandPageableResponse;
    }

    @Override
    public BrandResponseDto findById(long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(NotFoundException::new);
        BrandResponseDto brandResponse = brandMapper.toBrandResponseDto(brand);
        return brandResponse;
    }

    @Override
    public BrandResponseDto save(BrandRequestDto brandRequestDto) {
        if(brandRepository.existsByName(brandRequestDto.getName())) throw new ExistsException();
        Brand brand = brandMapper.toBrand(brandRequestDto);
        brand = brandRepository.save(brand);
        BrandResponseDto brandResponse = brandMapper.toBrandResponseDto(brand);
        return brandResponse;
    }

    @Override
    public BrandResponseDto update(BrandRequestUpdateDto brandRequestUpdateDto) {
        if(!brandRepository.existsById(brandRequestUpdateDto.getId()))throw new NotFoundException();
        if(brandRepository.existsByName(brandRequestUpdateDto.getName())) throw new ExistsException();

        Brand brand = brandMapper.toBrandUpdate(brandRequestUpdateDto);
        brand = brandRepository.save(brand);
        BrandResponseDto brandResponse = brandMapper.toBrandResponseDto(brand);
        return brandResponse;
    }

    @Override
    public boolean delete(long id) {
        if(!brandRepository.existsById(id))throw new NotFoundException();
        brandRepository.deleteById(id);
        return true;
    }
}
