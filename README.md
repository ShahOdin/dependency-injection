# Alternative dependency injection patterns in Scala

## Requirements

- [scala-cli](https://scala-cli.virtuslab.org/install) 
- [colordiff](https://formulae.brew.sh/formula/colordiff) (optional)

## Comparisons

| Link                                 | Run                                          | Comparison with Vanila approach                                |
|--------------------------------------|----------------------------------------------|----------------------------------------------------------------|
| [Vanila approach](docs/Vanila.md)     | `scala-cli ./models docs/Vanila.md`          | N/A                                                            |
| [Using Guice](docs/Guice.md)          | `scala-cli ./models docs/Guice.md`           | `colordiff ./docs/Vanila.md ./docs/Guice.md --side-by-side`      |
| [Implicitly](docs/Implicitly.md)      | `scala-cli ./models docs/Implicitly.md`      | `colordiff ./docs/Vanila.md ./docs/Implicitly.md --side-by-side` |
| [ImplicitlyTyped](docs/ImplicitlyTyped.md) | `scala-cli ./models docs/ImplicitlyTyped.md` | `colordiff ./docs/Vanila.md ./docs/ImplicitlyTyped.md --side-by-side` |
| [ExplicitlyUsing](docs/ExplicitlyUsing.md) | `scala-cli ./models docs/ExplicitlyUsing.md` | `colordiff ./docs/Vanila.md ./docs/ExplicitlyUsing.md --side-by-side` |

## Discuss

Any favorites? Pet peeves?... How would the general approaches introduced here compare to the established patterns such as:

- Cat: [Reader](https://typelevel.org/cats/api/cats/data/package$$Reader$.html), [Kleisli](https://typelevel.org/cats/datatypes/kleisli.html) 
- Cats MTL: [Ask](https://typelevel.org/cats-mtl/mtl-classes/ask.html)
- Cake pattern
- ZIO