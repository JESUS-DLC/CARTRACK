package dev.jesusdlc.cartrack.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Brand.class)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer year;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "user_id")
    private Usuario user;

}
