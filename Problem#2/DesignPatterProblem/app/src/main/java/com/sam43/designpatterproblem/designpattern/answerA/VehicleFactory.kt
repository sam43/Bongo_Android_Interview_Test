package com.sam43.designpatterproblem.desingpattern.answerA

class VehicleFactory {
    companion object {
        fun createVehicle(type: VehicleType?): Vehicle? =
            when (type) {
                VehicleType.CAR ->
                    Car(num_of_wheels = 4, num_of_passengers = 4, has_gas = true)
                VehicleType.PLANE ->
                    Plane(num_of_wheels = 5, num_of_passengers = 184, has_gas = true)
                else -> {
                    println("Requested vehicle type is not registered yet. \nCurrently we are having only CAR and PLANES")
                    null
                }
            }
    }
}

enum class VehicleType {
    CAR, PLANE
}
