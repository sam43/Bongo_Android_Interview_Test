package com.sam43.designpatterproblem.desingpattern.answerA

class Car(
    private val num_of_wheels: Int,
    private val num_of_passengers: Int,
    private val has_gas: Boolean
) : Vehicle {

    override fun set_num_of_wheels(): Int = num_of_wheels

    override fun set_num_of_passengers(): Int = num_of_passengers

    override fun has_gas(): Boolean = has_gas

    fun getNumberOfWheels() = num_of_wheels
    fun getNUmberOfPassengers() = num_of_passengers
    fun hasGas() = has_gas

    fun drive() {
        println("Car is driving...")
    }
}