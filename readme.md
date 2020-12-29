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
* [...primary](./src/main/java/com/github/cbuschka/hexarch_eval/primary/) - primary actors, also called drivers; a web controller
* [...secondary](./src/main/java/com/github/cbuschka/hexarch_eval/secondary/) - secondary actors, also called drivens; a jpa repository
* [...infrastructure](./src/main/java/com/github/cbuschka/hexarch_eval/infrastructure/) - spring plumbing

## License
[MIT](./license.txt)
