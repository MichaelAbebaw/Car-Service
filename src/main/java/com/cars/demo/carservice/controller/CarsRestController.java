package com.cars.demo.carservice.controller;

import com.cars.demo.carservice.entity.Car;
import com.cars.demo.carservice.service.CarService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarsRestController {

    private final CarService carService;

    public CarsRestController(CarService carService) {
        this.carService = carService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getCars(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "mpg", params = "mpg", spec = Equal.class),
                    @Spec(path = "cylinder", params = "cylinder", spec = Equal.class),
                    @Spec(path = "displacement", params = "displacement", spec = Equal.class),
                    @Spec(path = "horsePower", params = "horsePower", spec = Equal.class),
                    @Spec(path = "weight", params = "weight", spec = Equal.class),
                    @Spec(path = "acceleration", params = "acceleration", spec = Equal.class),
                    @Spec(path = "model", params = "model", spec = Equal.class),
                    @Spec(path = "car", params = "car", spec = Like.class),
                    @Spec(path = "origin", params = "origin", spec = Like.class)
            }) Specification<Car> spec) {
        return carService.getCarsWithSpec(spec);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car addNewCar (@RequestBody Car car) {
        carService.addCar(car);
        return car;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car updateCar (@RequestParam(name = "id", required = true) Integer id, @RequestBody Car car) {
        Optional<Car> update = carService.updateCar(id, car);
        if (update.isPresent())
            return update.get();
        return null;
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteCar(@RequestParam(name = "id", required = true) Integer id) {
        carService.deleteCar(id);
        return HttpStatus.OK;
    }
}
