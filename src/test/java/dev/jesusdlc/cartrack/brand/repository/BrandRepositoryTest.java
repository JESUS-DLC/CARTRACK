package dev.jesusdlc.cartrack.brand.repository;

import dev.jesusdlc.cartrack.AbstractTestContainers;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.persistence.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BrandRepositoryTest extends AbstractTestContainers {

    @Autowired
    private BrandRepository brandRepository;

    private Brand brand;

    @BeforeEach
    void setUp(){
        brand = new Brand(1L,"NISSAN");
    }



    @DisplayName("verify if you can save a brand")
    @Test
    void canSaveBrand(){
        Brand brandSaved = brandRepository.save(brand);
        assertNotNull(brandSaved);
    }

    @DisplayName("verify if brand exist")
    @Test
    void verifyBrandName(){
        String brandName = "NISSAN";
        brandRepository.save(brand);

        boolean expected = brandRepository.existsByName(brandName);
        assertTrue(expected);
    }

    @Test
    void canListBrands(){
        Brand secondBrand = new Brand(2L,"CHEVROLET");
        brandRepository.save(brand);
        brandRepository.save(secondBrand);
        List<Brand> brands = brandRepository.findAll();
        assertEquals(2,brands.size());
    }

    @DisplayName("verify if pagination works")
    @Test
    void findAllPageable(){
        Brand secondBrand = new Brand(2L,"CHEVROLET");
        brandRepository.save(brand);
        brandRepository.save(secondBrand);
        Pageable pageable = PageRequest.of(0,2);
        Page<Brand> brands = brandRepository.findAll(pageable);

        assertEquals(2,brands.getContent().size());
    }


    @Test
    void deleteBrand(){
        brandRepository.save(brand);
        brandRepository.deleteById(brand.getId());
        Optional<Brand> brandDeleted = brandRepository.findById(brand.getId());
        assertTrue(brandDeleted.isEmpty());
    }

}
