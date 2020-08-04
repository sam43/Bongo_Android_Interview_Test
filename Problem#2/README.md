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
      
The given interface indicates towards the **Factory design pattern**. Here an interface **Vehicle**, is given to build two types of objects like **Car** and **Plane**. This provides a good platform to use the factory pattern and an opportunity to change the values of the objects on the fly.

To create a car and a plane class, we will simply keep Vehicle interface as the base to provide basic properties and functionalities for both Car and Plane class. The key point of factory design pattern is that we define a class/interface and then we can have subclasses which implement the contract defined by the base class. Here the subclasses are Car and Plane class. To build a car and a plane class dynamically, we will be using the constructor params to provide the property to the sub-classes.

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

Both Car and Plane classes implemented the Vehicle interface to override the base variables *set_num_of_wheels()*, *set_num_of_passengers()* and *has_gas()* to set their own attributes and properties there. Now we can simply build the car and plane class from our main/driver class like below:

      fun main() {
            // Ans of Question 2(a) 
          lateinit var vehicle: Vehicle
          vehicle = VehicleFactory.createVehicle(VehicleType.CAR) as Car
          vehicle.drive()
          vehicle = VehicleFactory.createVehicle(VehicleType.PLANE) as Plane
          vehicle.fly()
      }
      
      
### Unit Test for solution 2(a): FactoryPatternUnitTest.kt

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
      
 Unit test code for the solution of Q#2(a) using **Factory Design Pattern**.
 
<br/>


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
            // Ans of Question 2(b)
            // TODO:: Build car using builder pattern
            val buildCar: CarBuilder = CarBuilder.Builder().withTotalWheels(4).withPassengerMaxCapacity(5)
                .withGasEngine(false).build()
            buildCar.drive()

            // TODO:: Build plane using builder pattern
            val buildPlane: PlaneBuilder = PlaneBuilder.Builder().withTotalWheels(3)
                .withPassengerCapacity(185).withGasEngine(true).build()
            buildPlane.fly()
        }


### Unit test for solution 2(b): BuilderDesignPatternTest.kt

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

Here is the unit test for the alternative solution to Q#2(b) using **Builder Design Pattern**.

