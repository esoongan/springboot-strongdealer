package com.strongdealer.mobile.domain.Car.Car4Sale;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Car4SaleRepository extends JpaRepository<Car4Sale, Long> {

    Optional<Car4Sale> findByUserId(Long userId);
}
