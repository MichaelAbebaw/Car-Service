package com.cars.demo.carservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String car;
    private Double mpg;
    private Integer cylinder;
    private Double displacement;
    private Double horsePower;
    private Double weight;
    private Double acceleration;
    private Integer model;
    private String origin;

    public Car () {};

    public Car setId (Integer id) {
        this.id = id;
        return this;
    }

    public Car setCar(String car) {
        this.car = car;
        return this;
    }

    public Car setMpg(Double mpg) {
        this.mpg = mpg;
        return this;
    }

    public Car setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
        return this;
    }

    public Car setDisplacement(Double displacement) {
        this.displacement = displacement;
        return this;
    }

    public Car setHorsePower(Double horsePower) {
        this.horsePower = horsePower;
        return this;
    }

    public Car setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Car setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
        return this;
    }

    public Car setModel(Integer model) {
        this.model = model;
        return this;
    }

    public Car setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getCar() {
        return car;
    }

    public Double getMpg() {
        return mpg;
    }

    public Integer getCylinder() {
        return cylinder;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public Double getHorsePower() {
        return horsePower;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public Integer getModel() {
        return model;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return id == ((Car) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
