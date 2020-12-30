# Hexagonal Architecture Evaluation
[![Build](https://github.com/cbuschka/hexarch-eval/workflows/build/badge.svg)](https://github.com/cbuschka/hexarch-eval)

### Example implementation of hexagonal architecture style with spring jpa

## Prerequisites
* java 11
* maven

## Build
```
mvn verify
```

## Packages
* [...core](./src/main/java/com/github/cbuschka/hexarch_eval/core/) - the core domain logic
* [...primary](./src/main/java/com/github/cbuschka/hexarch_eval/primary/) - primary actors, also called drivers; a web controller and a mq subscriber
* [...secondary](./src/main/java/com/github/cbuschka/hexarch_eval/secondary/) - secondary actors, also called drivens; a jpa repository and a mq publisher
* [...infrastructure](./src/main/java/com/github/cbuschka/hexarch_eval/infrastructure/) - spring plumbing

## Questions
* Who controls the transaction? Currently it is duplicated in primary actors.
* Is introduction of an interface (compile time dependency) really inversion of the dependency direction? Or is an interface more than a code reference?
* Do I really need the extra interfaces introduced for a single implementation? Ist referencing the interface of the implementation ok?
* How do I prevent copying objects because of port boundaries?
* What prevents me from building a non-anemic domain model with JPA?
* Are spring data JPA repository and entity with annotations part of core domain?
* Is an isolated core domain with methods and data combined really worth the hassle? What's about functional decomposition into use cases, what keeps the classes small?
* Is hexegonal architecture overkill? Especially in the context of small microservices?

## References


## License
[MIT](./license.txt)
