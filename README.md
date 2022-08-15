# Purpose

The sos-endpoint accepts JSON as simple text and puts it on a topic. 

# Project Structure

* `src/main/java` - the service source code
* `src/main/kubernetes` - `Kubernetes` artifacts

This is a `Spring Boot` application which is deployed in `Kubernetes`. The container is built using the `jib` plugin
for `Maven`.

## Configuration

The application configuration is handled through a `Kubernetes` `ConfigMap` which is automatically read in by `Spring
Boot` at deployment time. The following properties are supported:

* [required]`mil.afdcgs.merlin.sos.kafka.bootstrap-server`: the `DNS` name of the `Kafka` bootstrap server to connect
  to.
* [default 1] `mil.afdcgs.merlin.sos.kafka.partition-count`: the number of partitions for each created topic.
* [default 1] `mil.afdcgs.merlin.sos.kafka.replica-count`: the number of partition replicas to create across the
  cluster. This number can't exceed the number of nodes in the cluster.


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

# Testing
To send data to the endpoint using `curl`:
```shell
curl -v sos-endpoint:8082/sos/sensor -H 'Content-Type:application/json' -d '{"name": "Elrond", "role": "Elf Lord"}'
```

# Deployment
## Local Development
For local development and testing, where the image can't be pushed to a `Docker` repository, then the image should be
uploaded to the `Kubernetes` node. The instructions given here are for `k3s`, but any locally-hosted `Kubernetes` should
provide an equivalent process.

```shell
$ kubectl apply -f src/main/kubernetes/merlin-sos-endpoint.yaml
```

## Test/Production Environments
In an environment where the service has been uploaded to a `Docker` repository, it can be deployed with:
```shell
$ kubectl apply -f src/main/kubernetes/merlin-sos-endpoint.yaml
```

## Uninstall
It can be removed with:
```shell
$ kubectl delete -f src/main/kubernetes/merlin-sos-endpoint.yaml
```



