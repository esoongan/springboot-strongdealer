package com.strongdealer.mobile.domain.Car.CarInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {


    Optional<Car> findByCarNo(String carNo);

    // 구현한 Specificaion객체를 주면 동적으로 조건 결합하여 검색가능
    @Override
    Page<Car> findAll(Specification<Car> spec, Pageable pageable);


}
