package com.sam43.designpatterproblem.builderpattern.answerB

import android.annotation.SuppressLint

class CarBuilder private constructor() {
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

        fun withPassengerMaxCapacity(numOfPassengers: Int): Builder {
            this.numOfPassengers = numOfPassengers
            return this
        }

        fun withGasEngine(willRunInGas: Boolean): Builder {
            this.willRunInGas = willRunInGas
            return this
        }

        fun build(): CarBuilder {
            val car =
                CarBuilder()
            car.numOfWheels = numOfWheels
            car.numOfPassengers = numOfPassengers
            car.willRunInGas = willRunInGas
            return car
        }
    }

    @SuppressLint("DefaultLocale")
    fun drive() {
        println(
            String.format(
                "Car using %d wheels, %d passengers and will run in gas %b", numOfWheels,
                numOfPassengers, willRunInGas
            )
        )
    }

    fun hasGas(): Boolean {
        return willRunInGas
    }
}