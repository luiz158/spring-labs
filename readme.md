# Warsaw Spring Labs

## Building

[Java][1] version 7 or higher is required to build the project.

Execute following in project root directory to build all:

 1. On Linux/Mac: `./mvnw clean install`
 2. On Windows: `./mvnw.bat clean install`

Project uses [Maven][1], [maven-wrapper][2] and [wrapper-maven-plugin][3].

Optionally, if you don't wnat to mess with your local settings, you can also:

 1. On Linux/Mac: `./mvnw -s .m2/settings.xml clean install`
 2. On Windows: `./mvnw.bat -s .m2/settings.xml clean install`

## Using

Find instructions for each lab in its sub-folder.

When in doubt refer to [the docs][5].

Labs index:

 1. [Introduction](lab01)
 2. [Container Basics](lab02)
 3. [Bean Lifecycle](lab03)
 4. [Testing Basics](lab04)
 5. [Java-Based Configuration](lab05)

 [1]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
 [2]: http://maven.apache.org
 [3]: https://github.com/bdemers/maven-wrapper
 [4]: https://github.com/rimerosolutions/maven-wrapper/wiki/Maven-Goals
 [5]: http://docs.spring.io/spring/docs/3.2.5.RELEASE/spring-framework-reference/html
