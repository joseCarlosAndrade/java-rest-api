# guide

simple rest api in springboot java using a postgresql database and jpa to manage ddl and entities

## running

set the postgresql configuration in application.properties

execute postgres

access postgres

```bash
psql -h localhost -p 5432 -d orders -U orders
```

run the app

either via ide or

```bash
./mvnw spring-boot:run
```

or single unit

```bash
# 1. Clean old classes and compile a production jar file
./mvnw clean package

# 2. Execute the self-contained production server
java -jar target/venture-api-0.0.1-SNAPSHOT.jar
```

test it with curl

```bash
curl -X GET http://localhost:8081/api/v1/orders \
     -H "Content-Type: application/json" | jq

curl -X POST http://localhost:8081/api/v1/orders \
    -H "Content-Type: application/json" \
    -d '{"productId": 101, "quantity": 3, "totalCost"  : 10.1}'
```
