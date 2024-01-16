package dev.jesusdlc.cartrack.persistence.repository;

import dev.jesusdlc.cartrack.domain.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Services,Long>, JpaSpecificationExecutor<Services> {
    @Query("SELECT s FROM Services s INNER JOIN s.car c INNER JOIN c.user u WHERE c.id = :carId AND u.username = :username")
    List<Services> findByCarIdAndCarUserUsername(@Param("carId") long carId, @Param("username") String username);
    @Query("SELECT s FROM Services s INNER JOIN s.car c INNER JOIN c.user u WHERE s.id =:id AND c.id =:carId AND u.username=:username")
    Optional<Services> findByIdAndCarIdAndUsername(@Param("id")long id, @Param("carId") long carId, @Param("username") String username);
    @Query("SELECT COUNT(s) > 0 FROM Services s INNER JOIN s.car c INNER JOIN c.user u WHERE s.id =:id AND c.id =:carId AND u.username=:username")
    Boolean existsByIdAndCarAndUsername(@Param("id")long id, @Param("carId") long carId, @Param("username") String username);
    @Query("SELECT s FROM Services s INNER JOIN s.car c INNER JOIN c.user u WHERE u.username = :username")
    List<Services> getTotalAllServicesByUser(@Param("username")String username);
    @Query("SELECT SUM(s.cost) FROM Services s INNER JOIN s.car c INNER JOIN c.user u WHERE u.username = :username")
    BigDecimal getTotalCostServices(@Param("username")String username);
}
