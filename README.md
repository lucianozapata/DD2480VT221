# Launch Interceptor Program 
<img align="right" width="150" height="150" src="https://cdn-icons-png.flaticon.com/512/2590/2590496.png">

### DD2480 | Assignment 1 | Group 24

[![CI Build](https://github.com/lucianozapata/DD2480VT221/actions/workflows/gradle.yml/badge.svg)](https://github.com/lucianozapata/DD2480VT221/actions/workflows/gradle.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Summary

This software is part of a hypothetical anti-ballistic missile system.
It can decide whether an interceptor should be launched based upon input radar tracking information.
It automatically determines the conditions relevant to the immediate situation and decides based
on planar data points representing radar echos.

## Setup 

### Requirements 
* Java JDK 11

For this project, the build tool [Gradle](https://gradle.org/) is used.
This project can be built with the help of the included wrapper without downloading this tool.
If an installation is preferred, please follow the operating system-dependent instructions on the linked web page.

### Build 
```bash
$ ./gradlew build       (Linux/Mac)
$ gradlew.bat build     (Windows)
```
### Tests
```bash
$ ./gradlew test        (Linux/Mac)
$ gradlew.bat test      (Windows)
```
Note: this task triggers the jacoco-plugin, which is used the determine the test coverage. 
To open the test-report, open build/jacocoHtml/index.html with your browser.

## Usage

As this software is only one part of a complete anti-ballistic missile system, it should be included as a package in other projects.

### Configuration 

The decision of the algorithm method depends on the given data points and the predefined parameters.
The parameters can be adjusted here: src/main/java/com/group24/decide/Parameter.java. 
The datapoints are provided here: src/main/java/com/group24/decide/Decide.java.
Note that after changing the parameters, you have to rebuild the project.

### Try it out 
To try out the program, some data points and an example configuration are given. The result of the algorithm is displayed in the console.
```bash
$ ./gradlew run        (Linux/Mac)
$ gradlew.bat run      (Windows)
```

## Documentation 

The JavaDoc documentation can be found [here](https://lucianozapata.github.io/DD2480VT221). <br>
To generate the documentation locally:
```bash
$ ./gradlew javadoc     (Linux/Mac)
$ gradlew.bat javadoc   (Windows)
```
To open the documentation, open build/docs/javadoc/index.html with your browser.

## Statement of contributions

| Name      |                    Features                    |
|:----------|:----------------------------------------------:|
| Yilin Chang    |                LIC4, LIC7, LIC8                |
| Nikolai Limbrunner | README, Skeleton, JavaDocs, LIC3, LIC13, LIC14 |
| Pontus Persman    |            Skeleton, Github Actions            |
| Rafi Youssef |                  README, LIC5                  |
| Luciano Zapata |                Skeleton, LIC0,                 |


