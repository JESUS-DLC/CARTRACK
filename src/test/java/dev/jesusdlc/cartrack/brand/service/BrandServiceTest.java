package dev.jesusdlc.cartrack.brand.service;

import dev.jesusdlc.cartrack.business.exception.ExistsException;
import dev.jesusdlc.cartrack.business.exception.NotFoundException;
import dev.jesusdlc.cartrack.business.mapper.BrandMapper;
import dev.jesusdlc.cartrack.business.service.impl.BrandServiceImpl;
import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandPageableResponse;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.persistence.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;
    @Spy
    private BrandMapper brandMapper = Mappers.getMapper(BrandMapper.class);
    @InjectMocks
    private BrandServiceImpl brandService;
    private Brand brand;
    private BrandRequestDto brandRequestDto;
    private BrandRequestUpdateDto brandRequestUpdateDto;

    @BeforeEach
    void setUp(){
        brand = new Brand(1L,"NISSAN");
        brandRequestDto = new BrandRequestDto("NISSAN");

        brandRequestUpdateDto = new BrandRequestUpdateDto();
        brandRequestUpdateDto.setId(1L);
        brandRequestUpdateDto.setName("CHEVROLET");
    }

    @Test
    void saveBrand(){
        given(brandRepository.existsByName(brandRequestDto.getName())).willReturn(false);
        given(brandRepository.save(any(Brand.class))).willReturn(brand);
        BrandResponseDto brandResponseDto = brandService.save(brandRequestDto);
        assertNotNull(brandResponseDto);
    }

    @Test
    void createException(){
        given(brandRepository.existsByName(brandRequestDto.getName())).willReturn(true);
        assertThrows(ExistsException.class,()->{
            brandService.save(brandRequestDto);
        });
    }

    @Test
    void findAll(){
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1L,"NISSAN"));
        brands.add(new Brand(2L,"HONDA"));
        brands.add(new Brand(3L,"BMW"));

        given(brandRepository.findAll()).willReturn(brands);

        List<BrandResponseDto> brandsResponseDto = brandService.findAll();
        assertEquals(brandsResponseDto.size(),brands.size());
    }

    @Test
    void findAllPageable(){
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1L,"NISSAN"));
        brands.add(new Brand(2L,"HONDA"));
        brands.add(new Brand(3L,"BMW"));
        Pageable pageable = PageRequest.of(0,3);
        given(brandRepository.findAll(pageable)).willReturn(new PageImpl<>(brands));

        BrandPageableResponse pageableResponse = brandService.findAllPageable(pageable);

        assertEquals(pageableResponse.getTotalBrands(),brands.size());
    }

    @Test
    void findBrandById(){
        long id = 1L;
        given(brandRepository.findById(id)).willReturn(Optional.of(brand));
        BrandResponseDto brandResponseDto = brandService.findById(id);
        assertEquals(brandResponseDto.getId(),id);
    }

    @Test
    void findBrandByIdException(){
        long id = 1L;
        given(brandRepository.findById(id)).willReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{
            brandService.findById(id);
        });
    }

    @Test
    void updateBrand(){
        Brand brandUpdated = new Brand(1L,"CHEVROLET");
        given(brandRepository.existsById(brandRequestUpdateDto.getId())).willReturn(true);
        given(brandRepository.existsByName(brandRequestUpdateDto.getName())).willReturn(false);
        given(brandRepository.save(any(Brand.class))).willReturn(brandUpdated);

        BrandResponseDto brandResponseDto = brandService.update(brandRequestUpdateDto);
        assertAll(()->{
            assertNotNull(brandResponseDto);
            assertEquals(brandUpdated.getId(),brandResponseDto.getId());
            assertEquals(brandUpdated.getName(),brandResponseDto.getName());
        });
    }

    @Test
    void deleteBrand(){
        long id =1L;
        given(brandRepository.existsById(id)).willReturn(true);
        willDoNothing().given(brandRepository).deleteById(id);
        boolean deleted = brandService.delete(id);
        assertTrue(deleted);
    }

    @Test
    void deleteException(){
        long id = 1L;
        given(brandRepository.existsById(id)).willReturn(false);
        assertThrows(NotFoundException.class,()->{
           brandService.delete(id);
        });
    }

}
