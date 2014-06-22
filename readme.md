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


## Eclipse users note

In main project pom.xml eclipse-groovy-compiler is declared and groovy is used in some labs. 
To allow eclipse support for groovy and maven integration (m2e) with groovy compiler
it is required to install following plugin according to instructions :
http://groovy.codehaus.org/Eclipse+Plugin                        
Further readings :
http://docs.codehaus.org/display/GROOVY/Groovy-Eclipse+compiler+plugin+for+Maven

After importing whole project in to Eclipse you may see errors in each subproject :
> Groovy: compiler mismatch Project level is: 2.2 Workspace level is 2.0                              
> Groovy compiler level expected by the project does not match workspace compiler level.

This is because spring-boot currently uses Groovy 2.2 and Eclipse plugin supports only 2.0 (as of 20-06-2014),
to make this errors disappear you need perform following steps for each project with above error :

   1.Go to Project properties -> Groovy Compiler                       
   2.Set groovy compiler for project to 2.0

Now you are good to go :) 


                             
                                                                                             
                                                                                                    

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
 9. [Spring Data JPA](lab09)
 10. [More Spring MVC](lab10)
 11. [Spring RESTful MVC & WebSockets](lab11)

 [1]: http://spring.io
 [2]: http://warszawa.jug.pl
 [3]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
 [4]: http://maven.apache.org
 [5]: https://github.com/bdemers/maven-wrapper
 [6]: https://github.com/rimerosolutions/maven-wrapper/wiki/Maven-Goals
 [7]: http://docs.spring.io/spring/docs/3.2.5.RELEASE/spring-framework-reference/html
