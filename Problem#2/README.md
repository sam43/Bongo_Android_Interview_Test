# Solution to Q#2: Design Pattern Problem
Project link here: https://github.com/sam43/Bongo_Android_Interview_Test/tree/master/Problem%232/DesignPatterProblem

Unit Test code (embedded to the project) here: https://github.com/sam43/Bongo_Android_Interview_Test/tree/master/Problem%232/DesignPatterProblem/app/src/test/java/com/sam43/designpatterproblem/designpatterntest


## Q#2(a) Explain how can you use the pattern to create car and plane class?
### Answer to the Q#2(a):

Given,

**Vehicle**

      interface Vehicle {
          fun set_num_of_wheels(): Int
          fun set_num_of_passengers(): Int
          fun has_gas(): Boolean
      }
      
The given interface indicates towards the Factory design pattern. Here, a interface is given to build two types of objects like Car and Plane. This provides a good platform to use the factory pattern and an opportunity to change the values of the objects on the fly.

To create a car and a plane class, we will simply keep Vehicle class as a base class. The key point of factory design pattern is that we define a class/interface and then we can have subclasses which implement the contract defined by the base class. Here the subclasses are Car and Plane class.

**Car.kt**

    class Car(
        private val num_of_wheels: Int,
        private val num_of_passengers: Int,
        private val has_gas: Boolean
    ) : Vehicle {
        override fun set_num_of_wheels(): Int = num_of_wheels

        override fun set_num_of_passengers(): Int = num_of_passengers

        override fun has_gas(): Boolean = has_gas

        fun drive() {
            println("Car is driving...")
        }
    }

**Plane.kt**

    class Plane(
        private val num_of_wheels: Int,
        private val num_of_passengers: Int,
        private val has_gas: Boolean
    ) : Vehicle {
        override fun set_num_of_wheels(): Int = num_of_wheels

        override fun set_num_of_passengers(): Int = num_of_passengers

        override fun has_gas(): Boolean = has_gas

        fun fly() {
            println("Plane is flying...")
        }
    }

Both Car and Plane classes implemented the Vehicle interface to override the base variables *set_num_of_wheels()*, *set_num_of_passengers()* and *has_gas()* to set their own attributes and properties there.


## Q#2(b) Use a different design pattern for this solution.
### Answer to the Q#2(b):

Another design pattern can be the **Builder Pattern** to this solution. Here is an implementation overview to solve this using builder pattern.

First, let's create **CarBuilder** class.
          
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


Now, we will build the **PlaneBuilder** class in a same way.

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

After creating these two classes. We will simply call 'build()' method with the builder classes to build Car and Plane respectively. Here is the **Controller.kt** class.

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







