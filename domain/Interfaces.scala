import Models.*

object Interfaces:
  trait CoffeeGrinder:
    def grind: CoffeeBean => GroundCoffee
  trait CoffeeMaker:
    def brew: GroundCoffee => Coffee
  trait Barista:
    protected def sell: Coffee => Money = coffee => Money(s"Money(${coffee.value})")
    def work: CoffeeBean => Money