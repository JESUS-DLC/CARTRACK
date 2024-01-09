package dev.jesusdlc.cartrack.car.repository;

import dev.jesusdlc.cartrack.AbstractTestContainers;
import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.domain.specification.SearchCarSpecification;
import dev.jesusdlc.cartrack.persistence.repository.BrandRepository;
import dev.jesusdlc.cartrack.persistence.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class CarRepositoryTest extends AbstractTestContainers {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BrandRepository brandRepository;

    private Car car;
    private Brand brandSaved;

    @BeforeEach
    void setUp(){
        Brand brand = Brand.builder().name("Nissan").build();
        brandSaved = brandRepository.save(brand);
        car = Car.builder().brand(brandSaved).model("Kicks").year(2018).build();
    }

    @Test
    void canSaveCar(){
        Car carSaved = carRepository.save(car);
        assertNotNull(carSaved);
    }

    @Test
    void canFindCarById(){
        Car carSaved = carRepository.save(car);
        Optional<Car> carFind = carRepository.findById(carSaved.getId());
        assertTrue(carFind.isPresent());
    }

    @Test
    void canFindAll(){
        Car car2 = Car.builder().brand(brandSaved).model("Skyline").year(2004).build();
        carRepository.save(car);
        carRepository.save(car2);
        List<Car> cars = carRepository.findAll();
        assertEquals(2,cars.size());
    }

    @Test
    void canFindAllSpecification(){
        Car car2 = Car.builder().brand(brandSaved).model("Skyline").year(2004).build();
        carRepository.save(car);
        carRepository.save(car2);

        SearchCarSpecification specification = new SearchCarSpecification(1L,2004L);

        List<Car> cars = carRepository.findAll(specification);
        assertEquals(1,cars.size());
    }

    @Test
    void canFindAllPageable(){
        Car car2 = Car.builder().brand(brandSaved).model("Skyline").year(2004).build();
        Car car3 = Car.builder().brand(brandSaved).model("Versa").year(2017).build();
        carRepository.save(car);
        carRepository.save(car2);
        carRepository.save(car3);

        Pageable page = PageRequest.of(0,2);

        Page<Car> carsPage = carRepository.findAll(page);
        assertAll(()->{
            assertEquals(page.getPageNumber(),carsPage.getNumber());
            assertEquals(page.getPageSize(),carsPage.getSize());
        });
    }

    @Test
    void canFindAllPageableSpecification(){
        Car car2 = Car.builder().brand(brandSaved).model("Skyline").year(2004).build();
        Car car3 = Car.builder().brand(brandSaved).model("Versa").year(2017).build();
        carRepository.save(car);
        carRepository.save(car2);
        carRepository.save(car3);

        Pageable page = PageRequest.of(0,2);
        SearchCarSpecification specification = new SearchCarSpecification(1L,2004L);

        Page<Car> carsPage = carRepository.findAll(specification,page);

        assertAll(()->{
            assertEquals(page.getPageNumber(),carsPage.getNumber());
            assertEquals(page.getPageSize(),carsPage.getSize());
            assertEquals(1,carsPage.getContent().size());
        });
    }

    @Test
    void canDeleteCar(){
        Car carSaved = carRepository.save(car);
        carRepository.deleteById(carSaved.getId());
        Optional<Car> carFind = carRepository.findById(carSaved.getId());
        assertTrue(carFind.isEmpty());
    }


}
