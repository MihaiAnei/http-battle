FROM mozilla/sbt:8u212_1.2.8 as builder

ADD . /code

WORKDIR /code

RUN sbt ";clean;compile;assembly"

FROM gcr.io/distroless/java:8

COPY --from=builder /code/target/scala-2.12/scala-finch-circe-assembly-0.1.jar /app/scala-finch-circe-assembly.jar

WORKDIR /app

CMD ["scala-finch-circe-assembly.jar"]