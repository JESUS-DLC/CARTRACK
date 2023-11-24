package dev.jesusdlc.cartrack.presentation.controller;

import dev.jesusdlc.cartrack.business.service.BrandService;
import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/{id}")
    ResponseEntity<BrandResponseDto> findById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findById(id));
    }
    @GetMapping("/all")
    ResponseEntity<List<BrandResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAll());
    }

    @GetMapping()
    ResponseEntity<PageableResponse<BrandResponseDto>> findAllPaged(@PageableDefault(page = 0,size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAllPageable(pageable));
    }

    @PostMapping
    ResponseEntity<BrandResponseDto> save(@Valid @RequestBody BrandRequestDto brandRequest){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.save(brandRequest));
    }
    @PatchMapping
    ResponseEntity<BrandResponseDto> update(@Valid @RequestBody BrandRequestUpdateDto brandRequest){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.update(brandRequest));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.delete(id));
    }

}
