object Models:
  opaque type CoffeeBean = String
  object CoffeeBean:
    def apply(s: String): CoffeeBean = s
    extension (s: CoffeeBean) def value: String = s

  opaque type GroundCoffee = String
  object GroundCoffee:
    def apply(s: String): GroundCoffee = s
    extension (s: GroundCoffee) def value: String = s

  opaque type Coffee = String
  object Coffee:
    def apply(s: String): Coffee = s
    extension (s: Coffee) def value: String = s

  opaque type Money = String
  object Money:
    def apply(s: String): Money = s
    extension (s: Money) def value: String = s