package dev.jesusdlc.cartrack.presentation.controller;

import dev.jesusdlc.cartrack.business.service.BrandService;
import dev.jesusdlc.cartrack.domain.dto.request.create.BrandRequestDto;
import dev.jesusdlc.cartrack.domain.dto.request.update.BrandRequestUpdateDto;
import dev.jesusdlc.cartrack.domain.dto.response.BrandResponseDto;
import dev.jesusdlc.cartrack.domain.dto.response.PageableResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
@Tag(name = "Brands")
public class BrandController {

    private final BrandService brandService;

    @Operation(
            summary = "Get a brand",
            description = "Get endpoint for brand by id"
    )
    @GetMapping("/{id}")
    ResponseEntity<BrandResponseDto> findById(@Parameter(description = "brand id",example = "2")
                                              @PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findById(id));
    }
    @Operation(
            summary = "Get all brands",
            description = "Get endpoint for all brands"
    )
    @GetMapping("/all")
    ResponseEntity<List<BrandResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAll());
    }

    @Operation(
            summary = "Get all brands with pagination",
            description = "Get endpoint for all brands with pagination"
    )
    @GetMapping()
    ResponseEntity<PageableResponse<BrandResponseDto>> findAllPaged(@ParameterObject @PageableDefault(page = 0,size = 10,sort = "name") Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAllPageable(pageable));
    }

    @Operation(
            summary = "Save a car",
            description = "Post endpoint for save a brand"
    )
    @PostMapping
    ResponseEntity<BrandResponseDto> save(@Valid @RequestBody BrandRequestDto brandRequest){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.save(brandRequest));
    }

    @Operation(
            summary = "Update a brand",
            description = "Patch endpoint for update a brand"
    )
    @PatchMapping
    ResponseEntity<BrandResponseDto> update(@Valid @RequestBody BrandRequestUpdateDto brandRequest){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.update(brandRequest));
    }

    @Operation(
            summary = "Delete a brand",
            description = "Delete endpoint for delete a brand by id"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@Parameter(description = "brand id",example = "2")
                                   @PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(brandService.delete(id));
    }

}
