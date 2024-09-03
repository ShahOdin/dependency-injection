
# Dependencies are passed implicitly

```scala 3
import Types.*
import Interfaces.*
import Impls.*
import scala.util.chaining.*

object Impls:
  object MyCoffeeGrinder:
    given CoffeeGrinder = new CoffeeGrinder:
      override def grind: CoffeeBean => GroundCoffee = (bean: CoffeeBean) =>
        GroundCoffee(s"GroundCoffee(${bean.value})")

  object MyCoffeeMaker:
    given CoffeeMaker = new CoffeeMaker:
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

val beans = Resources.beans

// impls
import MyCoffeeGrinder.given
import MyCoffeeMaker.given
val barista = MyBarista.apply

// app
beans
.map(barista.work)
.foreach(println)
```

# Observations

- less boilerplate
- bit too 'implicit'?
- explicit import? coherence vs incoherence
- perhaps we could limit its use to dependency injection?