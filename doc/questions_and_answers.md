# Questions and Answers

## Questions Who controls the transaction?
Transaction logic is controlled by outbound adapters.

## Is introduction of an interface (compile time dependency) really inversion of the dependency direction? Or is an interface more than a code reference?
to be answered

## Do I really need the extra interfaces introduced for a single implementation? Is referencing the interface of the implementation ok?
to be answered

## How do I prevent copying objects because of port boundaries?
to be answered

## What prevents me from building a non-anemic domain model with JPA?
to be answered

## Are spring data JPA repository and entity with annotations part of core domain?
to be answered

## Is an isolated core domain with methods and data combined really worth the hassle? What's about functional decomposition into use cases, what keeps the classes small?
to be answered

## Is hexegonal architecture overkill? Especially in the context of small microservices?
to be answered

## Where to put retry logic?
to be answered

## How to implement optimistic locking?
I decided to be pragmatic and added a ```versionLoaded``` field to domain object.

## Where to place security checks?
to be answered