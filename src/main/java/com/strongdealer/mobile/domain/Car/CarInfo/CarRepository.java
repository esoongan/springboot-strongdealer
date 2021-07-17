package com.strongdealer.mobile.domain.Car.CarInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByCarNo(String carNo);


}
