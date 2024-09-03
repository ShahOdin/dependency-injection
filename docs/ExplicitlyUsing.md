# Given, Using

```scala 3
import Models.*
import Interfaces.*
import Impls.*
import scala.util.chaining.*

object Impls:
  object MyCoffeeGrinder:
    def apply: CoffeeGrinder = new CoffeeGrinder:
      override def grind: CoffeeBean => GroundCoffee = (bean: CoffeeBean) =>
        GroundCoffee(s"GroundCoffee(${bean.value})")

  object MyCoffeeMaker:
    def apply: CoffeeMaker = new CoffeeMaker:
      override def brew: GroundCoffee => Coffee = (groundCoffee: GroundCoffee) =>
        Coffee(s"Coffee(${groundCoffee.value})")

  object MyBarista:
    def apply(using grinder: CoffeeGrinder, coffeeMaker: CoffeeMaker): Barista = new Barista:
      override def work: CoffeeBean => Money = coffeeBean =>
        coffeeBean
          .pipe(grinder.grind)
          .pipe(coffeeMaker.brew)
          .pipe(coffee => Money(s"Money(${coffee.value})"))

object Resources:
  val beans: List[CoffeeBean] = List.fill(5)(CoffeeBean("<>"))

given beans: List[CoffeeBean] = Resources.beans

// impls
given CoffeeGrinder = MyCoffeeGrinder.apply
given CoffeeMaker = MyCoffeeMaker.apply
val barista = MyBarista.apply

// app
beans
  .map(barista.work)
  .foreach(println)
```