# The general approach in scala

Ignoring Resource acquisition, async instance creation etc. for now.  

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
    def apply(grinder: CoffeeGrinder, coffeeMaker: CoffeeMaker): Barista = new Barista:
      override def work: CoffeeBean => Money = coffeeBean =>
        coffeeBean
          .pipe(grinder.grind)
          .pipe(coffeeMaker.brew)
          .pipe(sell)

object Resources:
  val beans: List[CoffeeBean] = List.fill(5)(CoffeeBean("<>"))

val beans = Resources.beans

// impls
val coffeeMaker: CoffeeMaker = MyCoffeeMaker.apply
val coffeeGrinder: CoffeeGrinder = MyCoffeeGrinder.apply
val barista = MyBarista(coffeeGrinder, coffeeMaker)

// app
beans
  .map(barista.work(_))
  .foreach(println)
```

# Observations

- Little bit verbose (is that a bad thing?)
- passing all params the same way, whether they are:
  - dynamic values
  - interface instances
  - config params
- ???