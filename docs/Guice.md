# Using Guice

```scala 3
//> using dep "net.codingwell::scala-guice:7.0.0"

import Models.*
import Interfaces.*
import Impls.*
import scala.util.chaining.*

object Impls:
    object MyCoffeeGrinder:
      def apply: CoffeeGrinder = new CoffeeGrinder:
        override def grind: CoffeeBean => GroundCoffee = (bean: CoffeeBean) => GroundCoffee(s"GroundCoffee(${bean.value})")

    object MyCoffeeMaker:
      def apply: CoffeeMaker = new CoffeeMaker:
        override def brew: GroundCoffee => Coffee = (groundCoffee: GroundCoffee) => Coffee(s"Coffee(${groundCoffee.value})")

    object MyBarista:
        def apply(grinder: CoffeeGrinder, coffeeMaker: CoffeeMaker): Barista = new Barista:
          override def work: CoffeeBean => Money = coffeeBean =>
            coffeeBean
              .pipe(grinder.grind)
              .pipe(coffeeMaker.brew)
              .pipe(coffee => Money(s"Money(${coffee.value})"))

//explicit-ish dep mapping
import com.google.inject.{AbstractModule, Guice, Injector, Provides}
class CoffeeModule extends AbstractModule:
    @Provides
    def provideCoffeeGrinder(): CoffeeGrinder = MyCoffeeGrinder.apply
    @Provides
    def provideCoffeeMaker(): CoffeeMaker = MyCoffeeMaker.apply
    @Provides
    def provideBarista(grinder: CoffeeGrinder, coffeeMaker: CoffeeMaker): Barista =
    MyBarista.apply(grinder, coffeeMaker)

object Resources:
  val beans: List[CoffeeBean] = List.fill(5)(CoffeeBean("<>"))
  
val beans = Resources.beans

// impls
val injector: Injector = Guice.createInjector(new CoffeeModule)
val barista: Barista = injector.getInstance(classOf[Barista])

// app
beans
.map(barista.work)
.foreach(println)
```

# Observations

- Guice is possibly an overkill?
- it does reduce the boilerplate somewhat
- side observation: is it a code smell if I find myself multiple modules? 🤔
- ???