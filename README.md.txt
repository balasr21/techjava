1.Description

This project is an REST API developed in Java using Spring boot.

This project retrieves data from  http://developer.marvel.com/

1.1 Retrieve character

 /characters returns all the Marvel Characters IDs only in Json Array
 
 Uses cache as Marvel API exposes max of only 100 characters at a time
 Cache is cleared every 24Hours
 
1.2 Retrieve character information

 /characters/{characterId}  returns specific inforation about the character id passed
 considering its valid
 
1.3 Retrieve character information and translates based on given language (Standard ISO-639-1)

  /characters/
  {characterId}?language={languageCode} - Retrieves specific information about the
  character id passed and translates to given language. 
  
2. Technical Details:

2.1 Tools&Framework:

   The below are the list of tools and framework used in the project!

Spring Boot framework
Maven for Packaging and Build
Java Programming language
Uses Google cloud Library for realtime translations
 
2.2 Key Features to highlight:
  1.Implemented cache to retrieve data for list of characters
  2.Handled invalid requests with appropriate error message
  3.Added swagger with specic information which will help usage of API
  
 
3.Swagger
 
 If this application is being accessed locally,then swagger UI can be accessed at
 
http://localhost:8080/swagger-ui.html#/ 

4.Run Application

If the application has to be run locally, it expects below parametrs

Private & Public keys from Marvel API website - https://developer.marvel.com/
API Key from Google Cloud for translation

Below command can be used to invoke the application by passing appropriate values as replacement to {{VALUE}}

mvn spring-boot:run -Dspring-boot.run.arguments="--marvel.private.key={{VALUE}},--marvel.public.key={{VALUE}},--google.cloud.api.key={{VALUE}}"


default user id is user
default password is password
