package com.sam43.designpatterproblem.desingpattern.answerA


fun main() {
    // Ans of Question (a)
    lateinit var vehicle: Vehicle
    vehicle = VehicleFactory.createVehicle(VehicleType.CAR) as Car
    vehicle.drive()
    vehicle = VehicleFactory.createVehicle(VehicleType.PLANE) as Plane
    vehicle.fly()
}