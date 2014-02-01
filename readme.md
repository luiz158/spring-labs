# Warsaw Spring Labs

A small project created to learn [Spring Framework][1] step by step, supported by [Warsaw JUG][2].

## Building

[Java][3] version 7 or higher is required to build the project.

Execute following in project root directory to build all:

 1. On Linux/Mac: `./mvnw clean install`
 2. On Windows: `./mvnw.bat clean install`

Project uses [Maven][4], [maven-wrapper][5] and [wrapper-maven-plugin][6].

Optionally, if you don't wnat to mess with your local settings, you can also:

 1. On Linux/Mac: `./mvnw -s .m2/settings.xml clean install`
 2. On Windows: `./mvnw.bat -s .m2/settings.xml clean install`

## Using

Find instructions for each lab in its sub-folder.

When in doubt refer to [the docs][7].

Labs index:

 1. [Introduction](lab01)
 2. [Container Basics](lab02)
 3. [Bean Lifecycle](lab03)
 4. [Testing Basics](lab04)
 5. [Java-Based Configuration](lab05)
 6. [Rebooted](lab06)
 7. [Transactions and other Cross-Cutting Concerns](lab07)
 8. [Introduction to Spring MVC](lab08)

 [1]: http://spring.io
 [2]: http://warszawa.jug.pl
 [3]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
 [4]: http://maven.apache.org
 [5]: https://github.com/bdemers/maven-wrapper
 [6]: https://github.com/rimerosolutions/maven-wrapper/wiki/Maven-Goals
 [7]: http://docs.spring.io/spring/docs/3.2.5.RELEASE/spring-framework-reference/html
