# Hexagonal Architecture Evaluation
[![Build](https://github.com/cbuschka/hexarch-eval/workflows/build/badge.svg)](https://github.com/cbuschka/hexarch-eval)

### An Example Implementation of Hexagonal Architecture Style with Spring JPA

## Prerequisites
* java 11
* maven

## Build
```
mvn verify
```

## Packages
* [...domain](./domain/src/main/java/com/github/cbuschka/hexarch_eval/domain/) - the core domain logic
* [...primary](./webapp/src/main/java/com/github/cbuschka/hexarch_eval/primary/) - primary actors, also called drivers; a web controller and a mq subscriber
* [...secondary](./webapp/src/main/java/com/github/cbuschka/hexarch_eval/secondary/) - secondary actors, also called drivens; a jpa repository and a mq publisher
* [...infrastructure](./webapp/src/main/java/com/github/cbuschka/hexarch_eval/infrastructure/) - spring plumbing

## Questions
* Who controls the transaction? Currently it is duplicated in primary actors.
  * Transaction logic is now controlled by secondary actors, as this matches modern distributed systems' reality.
* Is introduction of an interface (compile time dependency) really inversion of the dependency direction? Or is an interface more than a code reference?
* Do I really need the extra interfaces introduced for a single implementation? Ist referencing the interface of the implementation ok?
* How do I prevent copying objects because of port boundaries?
* What prevents me from building a non-anemic domain model with JPA?
* Are spring data JPA repository and entity with annotations part of core domain?
* Is an isolated core domain with methods and data combined really worth the hassle? What's about functional decomposition into use cases, what keeps the classes small?
* Is hexegonal architecture overkill? Especially in the context of small microservices?
* Where to put retry logic? 

## References
* [My preferred walk through](https://hackernoon.com/decoupling-your-technical-code-from-your-business-logic-with-the-hexagonal-architecture-c73y23zs4)
* [Original post about the hexagonal architecture by Alistair Cockburn](http://archive.is/5j2NI)
* [Netflix post on a project using a hexagonal architecture](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749)

* [Hex arch, but mixed up with many other things](https://fideloper.com/hexagonal-architecture)

## License
[MIT](./license.txt)
