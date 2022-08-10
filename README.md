# Purpose

The mock message producer is used to simulate streams of data being sent to Kafka topics.

# Project Structure

* `src/main/java` - the service source code
* `src/main/test` - unit tests
* `src/main/kubernetes` - `Kubernetes` artifacts

This is a `Spring Boot` application which is deployed in `Kubernetes`. The container is built using the `jib` plugin
for `Maven`.

## Configuration

The application configuration is handled through a `Kubernetes` `ConfigMap` which is automatically read in by `Spring
Boot` at deployment time. The following properties are supported:

* [required]`mil.afdcgs.merlin.mockmessage.kafka.bootstrap-server`: the `DNS` name of the `Kafka` bootstrap server to 
  connect to.
* [required]`mil.afdcgs.merlin.mockmessage.target-topic`: the `DNS` name of the `Kafka` topic to send messages to. Note
that this topic must already exist.

## Dependencies

The project uses these modules:

* `spring-boot-starter-actuator` - This module allows us to monitor the service health and metrics through `HTTP`.
* `spring-kafka` - a Spring library for connecting to `Kafka` and using it in a Spring-like fashion.

# Build

To build the project:
```shell
mvn clean install
```

This will create a `Docker` container image and upload it to the local `Docker` repository.

# Deployment
## Local Development
For local development and testing, where the image can't be pushed to a `Docker` repository, then the image should be
uploaded to the `Kubernetes` node. The instructions given here are for `k3s`, but any locally-hosted `Kubernetes` should
provide an equivalent process.

```shell
$ docker save --output=target/mock-message-producer-latest.tar mock-message-producer:latest
```
```shell
$ sudo k3s ctr images import target/mock-message-producer-latest.tar
```
```shell
$ kubectl apply -f src/main/kubernetes/merlin-phase1-mockmessageproducer.yaml
```

## Test/Production Environments
In an environment where the service has been uploaded to a `Docker` repository, it can be deployed with:
```shell
$ kubectl apply -f src/main/kubernetes/merlin-phase1-mockmessageproducer.yaml
```

## Uninstall
It can be removed with:
```shell
$ kubectl delete -f src/main/kubernetes/merlin-phase1-mockmessageproducer.yaml
```



