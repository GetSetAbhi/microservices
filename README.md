# microservices
Normal Microservices

Use following url for udemy-microservices

http://localhost:8080/limits

http://localhost:8000/currency-exchange/from/USD/to/INR

http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

http://localhost:8100/currency-conversion/feign/from/USD/to/INR/quantity/10

# Eureka server Url
http://localhost:8761/

#Api Gateway url

Lower Case

- http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion/currency-conversion-feign/from/USD/to/INR/quantity/10

# Custom routes

- http://localhost:8765/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/CURRENCY-CONVERSION/from/USD/to/INR/quantity/10

Url for currency service that calls currency exchange service via load balancer
- http://localhost:8765/currency-conversion/test/from/USD/to/INR/quantity/10