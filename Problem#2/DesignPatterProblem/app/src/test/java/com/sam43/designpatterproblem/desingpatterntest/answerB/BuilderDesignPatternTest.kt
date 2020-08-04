package com.sam43.designpatterproblem.answerB

import com.sam43.designpatterproblem.desingpattern.answerB.CarBuilder
import com.sam43.designpatterproblem.desingpattern.answerB.PlaneBuilder
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class BuilderDesignPatternTest {
    @Test
    fun testCarBuilderOnSuccessCase() {
        val car: CarBuilder =
            CarBuilder.Builder().withTotalWheels(4).withPassengerMaxCapacity(5)
                .withGasEngine(false).build()
        assertEquals(4, car.numOfWheels)
        assertEquals(5, car.numOfPassengers)
        assertEquals(false, car.hasGas())
    }

    @Test
    fun testPlaneBuilderOnSuccessCase() {
        val plane: PlaneBuilder = PlaneBuilder.Builder().withTotalWheels(5)
            .withPassengerCapacity(185).withGasEngine(true).build()

        assertEquals(5, plane.numOfWheels)
        assertEquals(185, plane.numOfPassengers)
        assertEquals(true, plane.hasGas())
    }

    @Test
    fun testCarBuilderOnFailureCase() {
        val car: CarBuilder =
            CarBuilder.Builder()
                .withTotalWheels(4)
                .withPassengerMaxCapacity(5)
                .withGasEngine(false)
                .build()


        assertNotEquals(40, car.numOfWheels)
        assertNotEquals(50, car.numOfPassengers)
        assertNotEquals(true, car.hasGas())
    }

    @Test
    fun testPlaneBuilderOnFailureCase() {
        val plane: PlaneBuilder = PlaneBuilder.Builder()
            .withTotalWheels(5)
            .withPassengerCapacity(185)
            .withGasEngine(false)
            .build()

        assertNotEquals(35, plane.numOfWheels)
        assertNotEquals(1585, plane.numOfPassengers)
        assertNotEquals(true, plane.hasGas())
    }
}