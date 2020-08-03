package com.sam43.designpatterproblem.builderpattern.answerB

import android.annotation.SuppressLint

class PlaneBuilder private constructor() {
    var numOfWheels = 0
        private set
    var numOfPassengers = 0
        private set
    private var willRunInGas = false

    class Builder {
        private var numOfWheels = 0
        private var numOfPassengers = 0
        private var willRunInGas = false
        fun withTotalWheels(wheels: Int): Builder {
            numOfWheels = wheels
            return this
        }

        fun withPassengerCapacity(numOfPassengers: Int): Builder {
            this.numOfPassengers = numOfPassengers
            return this
        }

        fun withGasEngine(willRunInGas: Boolean): Builder {
            this.willRunInGas = willRunInGas
            return this
        }

        fun build(): PlaneBuilder {
            val plane =
                PlaneBuilder()
            plane.numOfWheels = numOfWheels
            plane.numOfPassengers = numOfPassengers
            plane.willRunInGas = willRunInGas
            return plane
        }
    }

    @SuppressLint("DefaultLocale")
    fun fly() {
        println(
            String.format(
                "Plane using %d wheels, %d passengers and will run in gas %b", numOfWheels,
                numOfPassengers, willRunInGas
            )
        )
    }

    fun hasGas(): Boolean {
        return willRunInGas
    }
}