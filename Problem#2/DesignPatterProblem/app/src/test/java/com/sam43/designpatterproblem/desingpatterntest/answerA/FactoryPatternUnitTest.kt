package com.sam43.designpatterproblem.desingpattern.answerA

import org.junit.Assert.*
import org.junit.Test

class FactoryPatternUnitTest {

    @Test
    fun testIfVehicleFactoryAcceptsNullSuccessCase() {
        val vehicle = VehicleFactory.createVehicle(null)
        assertTrue(vehicle == null)
    }

    @Test
    fun testIfCarAndVehicleIsReferringToTheSameCarSuccessCase() {
        val vehicle = VehicleFactory.createVehicle(VehicleType.CAR) as Car
        val car = Car(4, 4, true)
        assertTrue(vehicle.getNumberOfWheels() == car.getNumberOfWheels())
        assertTrue(vehicle.getNUmberOfPassengers() == car.getNUmberOfPassengers())
        assertTrue(vehicle.hasGas() == car.hasGas())
    }

    @Test
    fun testIfCarAndVehicleIsReferringToTheSameCarFailureCase() {
        val vehicle = VehicleFactory.createVehicle(VehicleType.CAR) as Car
        val car = Car(3, 18, false)
        assertFalse(vehicle.getNumberOfWheels() == car.getNumberOfWheels())
        assertFalse(vehicle.getNUmberOfPassengers() == car.getNUmberOfPassengers())
        assertFalse(vehicle.hasGas() == car.hasGas())
    }


    @Test
    fun testIfPlaneAndVehicleIsReferringToTheSamePlaneSuccessCase() {
        val vehicle = VehicleFactory.createVehicle(VehicleType.PLANE) as Plane
        val plane = Plane(5, 184, true)
        assertTrue(vehicle.getNumberOfWheels() == plane.getNumberOfWheels())
        assertTrue(vehicle.getNUmberOfPassengers() == plane.getNUmberOfPassengers())
        assertTrue(vehicle.hasGas() == plane.hasGas())
    }

    @Test
    fun testIfPlaneAndVehicleIsReferringToTheSamePlaneFailureCase() {
        val vehicle = VehicleFactory.createVehicle(VehicleType.PLANE) as Plane
        val plane = Plane(3, 8, false)
        assertFalse(vehicle.getNumberOfWheels() == plane.getNumberOfWheels())
        assertFalse(vehicle.getNUmberOfPassengers() == plane.getNUmberOfPassengers())
        assertFalse(vehicle.hasGas() == plane.hasGas())
    }


    @Test
    fun testIfCarVehicleIsNullFailCase() {
        val vehicle = VehicleFactory.createVehicle(VehicleType.CAR)
        assertFalse(vehicle == null)
    }


    @Test
    fun testIfPlaneVehicleIsNullFailCase() {
        val vehicle = VehicleFactory.createVehicle(VehicleType.PLANE)
        assertFalse(vehicle == null)
    }

}