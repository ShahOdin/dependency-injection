# Alternative syntax for implicits (and more?)

see the documentation on it here: https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html
and here is a useful article on it: https://blog.softwaremill.com/context-is-king-20f533474cb3

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
    def apply: CoffeeGrinder ?=> CoffeeMaker ?=> Barista = new Barista:
      override def work: CoffeeBean => Money = coffeeBean =>
        coffeeBean
          .pipe(summon[CoffeeGrinder].grind)
          .pipe(summon[CoffeeMaker].brew)
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

- ???