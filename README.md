## Solution Explanation

Assuming the time suggestion and requirements, I approached the problem using a rather simple approach.
As the program should start up quickly and be ready for search, the input terminal allows starts
up with the program and an executor does the processing of the locations.

Two approaches were to pre-parse all of the locations and work out distances, which would allow for
the quickest response time, but went with the current approach due to the startup requirement.

There are currently some chances of duplicate work being executed, if a location has not yet been
processed and a user queries that location while the thread is executing that reference. This
could be fixed using locks and checks around the state.

### Assumptions
I made the assumption that we couldn't add any extra resource files, and that the locations json 
file was extendable, or could be updated at any point to include different locations, and therefore
processing at startup was necessary each time rather than serializing the data (see indexing idea).


### Starting the App
- Added the maven assembly plugin, which produces a jar called ```backend-challenge.jar```
- To run the jar, run the ```mvn package``` to assemble the jar file and then run 
```java -jar backend-challenge.jar``` to start execution.
- Alternatively running the App.main() if in an IDE will also suffice.


# NurtureCloud Backend Challenge

In a real world scenario I think I would have gone with an indexing system such as Lucene where 
the locations would be indexed (prior to startup, and this also makes the solution a lot more 
scalable) and can be loaded on startup or just referenced on demand from the cache. 

## Scenario

As a real estate tech company, a common use case that our buyers have is wanting to search for properties in and around particular suburbs.  
We start with a list of suburbs and locations and use algorithms to calculate which suburbs should appear in their search.

Your task is to create a search mechanism that efficiently calculates nearby suburbs using provided locations and presents a list of options to a user.

## Requirements

You are provided with a file of Australian suburbs with their locations: ```src/main/resources/aus_suburbs.json```

~~~
...
{
  "Pcode": 2000,
  "Locality": "SYDNEY",
  "State": "NSW",
  "Comments": "",
  "Category": "Delivery Area",
  "Longitude": 151.2099,
  "Latitude": -33.8697
}
...
~~~

Use the suburb location data to calculate nearby suburbs from the list.

Some reading you may find useful on this subject: https://en.wikipedia.org/wiki/Haversine_formula

Requirements:

* The user can search with a Postcode and Locality and promptly get a response.
* The pair of Postcode and Locality is unique, but each field on its own is not.
* Categorise and organise nearby suburbs to close (within 10km) and fringe (within 50km).
* Maximum 15 nearby suburbs returned for each search.
* Maximum 15 fringe suburbs returned for each search.
* Results should be ordered by nearest to furthest.
* Should start up and be ready for search quickly.
* Provide an interactive command line interface. Make the input case-insensitive.

Example:
~~~
> Please enter a suburb name: Sydney
> Please enter the postcode: 2000

Nearby Suburbs:
    WOOLLOOMOOLOO 2011 
    THE ROCKS 2000 
    BARANGAROO 2000 
    ...

Fringe Suburbs
    NORTH BALGOWLAH 2093 
    BURWOOD HEIGHTS 2136
    MORTLAKE 2137
    ...

> Please enter a suburb name:
~~~

## What you will be assessed upon

* Reads the provided input and writes correct output.
* Is efficient in terms of CPU time and memory usage.
* Is simple, readable, understandable.
* Code structure and reasonable separation of concerns.
* Efficient test coverage and style.
* What trade-offs for memory vs CPU you make to give the optimum solution for the user.
* Handling of edge cases and input validation.
* Choice of libraries.

## Constraints & Guide

* Please answer in either Java (8+) or Kotlin
* This challenge should take 1-2 hours.

## Submission

* Zip up your file.
* Send to michal@upside.com.au
