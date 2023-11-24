package dev.jesusdlc.cartrack.brand.mapper;

import dev.jesusdlc.cartrack.business.mapper.BrandMapper;
import dev.jesusdlc.cartrack.business.mapper.BrandMapperImpl;
import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandMapperTest {

    private final BrandMapper brandMapper = new BrandMapperImpl();


    @Test
    void canMapToBrand(){
        BrandRequestDto brandRequestDto = new BrandRequestDto("NISSAN");
        Brand brand = brandMapper.toBrand(brandRequestDto);

        assertEquals(brandRequestDto.getName(),brand.getName());
    }

    @Test
    void canMapToBrandByUpdate(){
        BrandRequestUpdateDto brandRequest = new BrandRequestUpdateDto();
        brandRequest.setId(1L);
        brandRequest.setName("CHEVROLET");

        Brand brand = brandMapper.toBrandUpdate(brandRequest);

        assertAll(()->{
            assertEquals(brand.getId(),brandRequest.getId());
            assertEquals(brand.getName(),brandRequest.getName());
        });
    }

    @Test
    void canMapToBrandResponse(){
        Brand brand = new Brand(1L,"BMW");
        BrandResponseDto brandResponse = brandMapper.toBrandResponseDto(brand);

        assertAll(()->{
            assertEquals(brandResponse.getId(),brand.getId());
            assertEquals(brandResponse.getName(),brand.getName());
        });
    }
}
