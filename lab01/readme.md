Lab 01 - intro
==
Topics
--
* Get to know the real world business domain you will apply Spring during the course.

* How to use layered architecture to partition an application into components with well defined responsibilities.

* How to use [dependency injection][1] to pass a component what it needs to work.

* How to program to interfaces to encapsulate implementation complexity.

* How to test application behaviour.

Instructions
--
1. Create `savings.service.PaybackBookKeeper` implementation class in `savings.service.impl` package.

2. Implement `PaybackBookKeeperImpl` configuration logic utilizing a constructor to inject (configure):

    * An `AccountRepository` to load `Account` objects to add payback to.

    * A `MerchantRepository` to load `Merchant` objects to calculate payback amount.

    * A `PaybackRepository` to tract confirmed paybacks for accounting and reporting.

3. Use interface JavaDoc and provided unit tests stubs to implement `PaybackBookKeeperImpl` application logic.

 [1]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#overview-dependency-injection
