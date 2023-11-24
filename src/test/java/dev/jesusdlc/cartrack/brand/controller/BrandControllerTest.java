package dev.jesusdlc.cartrack.brand.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jesusdlc.cartrack.business.service.impl.BrandServiceImpl;
import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BrandServiceImpl brandService;
    @Autowired
    private ObjectMapper mapper;
    private BrandResponseDto brandResponseDto;

    @BeforeEach
    void setUp(){
        brandResponseDto = new BrandResponseDto(1L,"NISSAN");
    }

    @Test
    void findById() throws Exception {
        long id = 1L;
        given(brandService.findById(id)).willReturn(brandResponseDto);

        ResultActions response = mockMvc.perform(get("/api/brand/{id}",id));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(brandResponseDto.getName()));
    }

    @Test
    void findAllPageable() throws Exception{
        List<BrandResponseDto> brands = new ArrayList<>();
        brands.add(brandResponseDto);
        brands.add(new BrandResponseDto(2L,"CHEVROLET"));
        brands.add(new BrandResponseDto(3L,"BMW"));

        Pageable pageable = PageRequest.of(0,2);

        PageableResponse<BrandResponseDto> brandPage = new PageableResponse<>();
        brandPage.setContent(brands);
        brandPage.setTotalPages(1);
        brandPage.setNumberPage(pageable.getPageNumber());
        brandPage.setSizePage(pageable.getPageSize());
        brandPage.setTotalBrands((long) brands.size());


        given(brandService.findAllPageable(any(Pageable.class))).willReturn(brandPage);

        ResultActions response = mockMvc.perform(get("/api/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pageable))
        );

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberPage").value(brandPage.getNumberPage()))
                .andExpect(jsonPath("$.totalBrands").value(brandPage.getTotalBrands()));
    }

    @Test
    void findAll() throws Exception{
        List<BrandResponseDto> brands = new ArrayList<>();
        brands.add(brandResponseDto);
        brands.add(new BrandResponseDto(2L,"CHEVROLET"));
        brands.add(new BrandResponseDto(3L,"BMW"));

        given(brandService.findAll()).willReturn(brands);

        ResultActions response = mockMvc.perform(get("/api/brand/all"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(brands.size()));
    }

    @Test
    void create() throws Exception{
        BrandRequestDto brandRequestDto = new BrandRequestDto("NISSAN");
        given(brandService.save(any(BrandRequestDto.class))).willReturn(brandResponseDto);

        ResultActions response = mockMvc.perform(post("/api/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(brandRequestDto)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(brandResponseDto.getId()))
                .andExpect(jsonPath("$.name").value(brandResponseDto.getName()));
    }

    @Test
    void deleteById() throws Exception{
        long brandId = 10;
        given(brandService.delete(brandId)).willReturn(true);

        ResultActions response = mockMvc.perform(delete("/api/brand/"+brandId));

        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception{
        BrandRequestUpdateDto brandRequestUpdateDto = new BrandRequestUpdateDto();
        brandRequestUpdateDto.setId(1L);
        brandRequestUpdateDto.setName("NISSAN");

        given(brandService.update(any(BrandRequestUpdateDto.class))).willReturn(brandResponseDto);

        ResultActions response = mockMvc.perform(patch("/api/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(brandRequestUpdateDto))
        );

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(brandRequestUpdateDto.getId()))
                .andExpect(jsonPath("$.name").value(brandRequestUpdateDto.getName()));

    }

}
