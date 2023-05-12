package com.cars.demo.carservice.service;

import com.cars.demo.carservice.entity.Car;
import com.cars.demo.carservice.repository.CarRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /* Returns cars list. */
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    /* Returns car list with some criteria. */
    public List<Car> getCarsWithSpec (Specification<Car> spec) {
        return carRepository.findAll(spec);
    }

    /* Add new car entity to the repository. */
    public void addCar (Car car) {
        carRepository.save(car);
    }

    /* Delete a car in the repository. */
    public void deleteCar (Integer id) {
        carRepository.deleteById(id);
    }

    /* Update an existing car details. */
    public Optional<Car> updateCar (Integer id, Car car) {
        return carRepository.findById(id).map(c -> {
            if (!car.getCar().isEmpty())
                c.setCar(car.getCar());
            if (car.getAcceleration() != null)
                c.setAcceleration(car.getAcceleration());
            if (car.getMpg() != null)
                c.setMpg(car.getMpg());
            if (car.getCylinder() != null)
                c.setCylinder(car.getCylinder());
            if (car.getDisplacement() != null)
                c.setDisplacement(car.getDisplacement());
            if (car.getHorsePower() != null)
                c.setHorsePower(car.getHorsePower());
            if (car.getWeight() != null)
                c.setWeight(car.getWeight());
            if (car.getModel() != null)
                c.setModel(car.getModel());
            if (!car.getOrigin().isEmpty())
                c.setOrigin(car.getOrigin());
            return carRepository.save(c);
        });
    }

    @PostConstruct
    public void initDatabase () {
        try (Scanner scanner = new Scanner(new File("cars.csv"))) {
            scanner.nextLine(); // skip header
            scanner.nextLine(); // skip data types
            while (scanner.hasNext()) {
                String[] row = scanner.nextLine().split(",");
                Car car = new Car()
                        .setCar(row[0])
                        .setMpg(Double.parseDouble(row[1]))
                        .setCylinder(Integer.parseInt(row[2]))
                        .setDisplacement(Double.parseDouble(row[3]))
                        .setHorsePower(Double.parseDouble(row[4]))
                        .setWeight(Double.parseDouble(row[5]))
                        .setAcceleration(Double.parseDouble(row[6]))
                        .setModel(Integer.parseInt(row[7]))
                        .setOrigin(row[8]);
                carRepository.save(car);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
