package com.sam43.designpatterproblem.desingpattern.answerB


fun main() {
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