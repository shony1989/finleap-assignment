               **_Finleap Weather Case Study_**
               
The Project uses
1. JAVA 8
2. Spring boot 2.1.0 Release
3. Spring Cloud Finchley Release
4. Feign Client for Rest Communication
5. Hystrix to fall back in case of error
6. lombok to reduce boiler plate code
7 Maven as build tool
8. cache ,simple cache with concurrent hash map

To start the application ,you can import any IDE ,eclipse with lombok or IDEA with lombok
as maven import
and run main class FinleapWeatherApplication.java
or directly run the Java -jar xxxx

the Swagger documentation can be found at http://localhost:8080/swagger-ui.html

To get the Metric Result hit the endpoint http://localhost:8080/data?city=xxxx
where xxx is the city name

The Exception Handling is done with control advice

The challenge or Idea to have a cache/ stored data of valid cities on finleap app
to provide one level more abstraction and prevent user injection

I have provided a proof of concept to store data in RDB as requirement may change 
and on different condition we can query and get the city etc is valid on not

For right city cache doesn't run as valid city query is already ran by open weather api

As data is refreshed in open weather api every 10 min. 
A simple Spring boot cache is provided with caches the result in controller API

and evicted after 10 min

Integration test and Unit test is mentioned in test directory




