# agile-software-development-principles-patterns-and-practices-groovy
Exercises and Solutions from Agile Software Development Principles, Patterns, and Practices in Groovy

[![Build Status](https://travis-ci.org/thomashan/agile-software-development-principles-patterns-and-practices-groovy.svg?branch=master)](https://travis-ci.org/thomashan/agile-software-development-principles-patterns-and-practices-groovy) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=thomashan.github.io%3Aagile-software-development-principles-patterns-and-practices-groovy&metric=alert_status)](https://sonarcloud.io/dashboard?id=thomashan.github.io%3Aagile-software-development-principles-patterns-and-practices-groovy) [![codecov](https://codecov.io/gh/thomashan/agile-software-development-principles-patterns-and-practices-groovy/branch/master/graph/badge.svg)](https://codecov.io/gh/thomashan/agile-software-development-principles-patterns-and-practices-groovy) [![Coverage Status](https://coveralls.io/repos/github/thomashan/agile-software-development-principles-patterns-and-practices-groovy/badge.svg)](https://coveralls.io/github/thomashan/agile-software-development-principles-patterns-and-practices-groovy)

**TODO** Enable scrutinizer

The original repository is located in [here](https://github.com/unclebob/PPP)

This is an uplift to the original solutions using
* JDK9+ (uses [Flow](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.html) for the command bus)
* groovy
* gradle
* junit5

We use the Following tech to improve the project

| Methodology   | Tool       | Web                   |
| ---           | ---        | ---                   |
| CI            | travisci   | https://travis-ci.org |
| Code analysis | Sonarcloud | https://sonarcloud.io |
| Code analysis | codecov    | https://codecov.io    |
| Code analysis | coveralls  | https://coveralls.io  |
| Code analysis | Scrutinizer | 

## Reading
For some in depth discussions on CQRS and event sourcing please refer to [this page](https://developer.ibm.com/languages/java/series/reactive-in-practice/).
It outlines the basics of what CQRS and event sourcing is also provides differences between writing model and reading model.
