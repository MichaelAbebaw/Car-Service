package com.cars.demo.carservice.repository;

import com.cars.demo.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
    // this is left empty with purpose
}
