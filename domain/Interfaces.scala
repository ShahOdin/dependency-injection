import Models.*

object Interfaces:
  trait CoffeeGrinder:
    def grind: CoffeeBean => GroundCoffee
  trait CoffeeMaker:
    def brew: GroundCoffee => Coffee
  trait Barista:
    def work: CoffeeBean => Money