package com.sam43.designpatterproblem.builderpattern

import com.sam43.designpatterproblem.builderpattern.answerA.Car
import com.sam43.designpatterproblem.builderpattern.answerA.Plane
import com.sam43.designpatterproblem.builderpattern.answerB.CarBuilder
import com.sam43.designpatterproblem.builderpattern.answerB.PlaneBuilder


fun main() {
    // Ans of Question (a)
    val car = Car(num_of_wheels = 4, num_of_passengers = 4, has_gas = true)
    car.drive()
    val plane = Plane(num_of_wheels = 3, num_of_passengers = 185, has_gas = true)
    plane.fly()

    // Ans of Question (b)
    // TODO:: Build car using builder pattern
    val buildCar: CarBuilder = CarBuilder.Builder().withTotalWheels(4).withPassengerMaxCapacity(5)
        .withGasEngine(false).build()
    buildCar.drive()

    // TODO:: Build plane using builder pattern
    val buildPlane: PlaneBuilder = PlaneBuilder.Builder().withTotalWheels(3)
        .withPassengerCapacity(185).withGasEngine(true).build()
    buildPlane.fly()
}