package dev.jesusdlc.cartrack.brand.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jesusdlc.cartrack.business.service.impl.BrandServiceImpl;
import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

}
